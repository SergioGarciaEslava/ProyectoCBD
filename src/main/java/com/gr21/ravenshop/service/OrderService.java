package com.gr21.ravenshop.service;

import com.gr21.ravenshop.dto.OrderCreateForm;
import com.gr21.ravenshop.dto.OrderLineForm;
import com.gr21.ravenshop.model.Address;
import com.gr21.ravenshop.model.Customer;
import com.gr21.ravenshop.model.Order;
import com.gr21.ravenshop.model.Product;
import com.gr21.ravenshop.repository.CustomerRepository;
import com.gr21.ravenshop.repository.OrderRepository;
import com.gr21.ravenshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(OrderCreateForm form) {
        Customer customer = customerRepository.findById(normalizeCustomerId(form.getCustomerId()))
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        List<Order.OrderLineItem> lineItems = form.getLines().stream()
                .filter(line -> !isBlank(line.getProductId()))
                .map(this::toLineItem)
                .toList();

        Order order = Order.createPending(lineItems);
        order.setCustomerId(customer.getId());
        order.setCustomerSnapshot(toCustomerSnapshot(customer));
        order.setShippingAddress(resolveShippingAddress(form.getShippingAddress(), customer));
        order.setOrderedAt(OffsetDateTime.now());

        return orderRepository.save(order);
    }

    public List<Order> listOrders() {
        return listOrders(null, null, null);
    }

    public List<Order> listOrders(String status, String customer, String minTotal) {
        return orderRepository.findByFilters(
                        normalizeFilter(status),
                        normalizeFilter(customer),
                        parseMinTotal(minTotal)
                ).stream()
                .map(this::enrichForListView)
                .toList();
    }

    public Optional<Order> findById(String orderId) {
        return orderRepository.findById(normalizeId(orderId))
                .map(this::enrichForDetailView);
    }

    private String normalizeId(String orderId) {
        return orderId.contains("/") ? orderId : "orders/" + orderId;
    }

    private String normalizeCustomerId(String customerId) {
        if (isBlank(customerId)) {
            return "";
        }
        return customerId.contains("/") ? customerId : "customers/" + customerId;
    }

    private String normalizeProductId(String productId) {
        if (isBlank(productId)) {
            return "";
        }
        return productId.contains("/") ? productId : "products/" + productId;
    }

    private Order.OrderLineItem toLineItem(OrderLineForm lineForm) {
        Product product = productRepository.findById(normalizeProductId(lineForm.getProductId()))
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        Order.OrderLineItem lineItem = new Order.OrderLineItem();
        lineItem.setProductId(product.getId());
        lineItem.setProductName(product.getName());
        lineItem.setCategory(product.getCategory());
        lineItem.setUnitPrice(product.getPrice());
        lineItem.setQuantity(lineForm.getQuantity());
        return lineItem;
    }

    private Order.CustomerSnapshot toCustomerSnapshot(Customer customer) {
        Order.CustomerSnapshot snapshot = new Order.CustomerSnapshot();
        snapshot.setCustomerId(customer.getId());
        snapshot.setFullName(customer.getFullName());
        snapshot.setEmail(customer.getEmail());
        snapshot.setCity(customerCity(customer));
        return snapshot;
    }

    private String resolveShippingAddress(String requestedShippingAddress, Customer customer) {
        if (!isBlank(requestedShippingAddress)) {
            return requestedShippingAddress;
        }
        return formatShippingAddress(customer.getAddress());
    }

    private Order enrichForDetailView(Order order) {
        order.recalculateTotals();
        ensureDerivedFields(order);
        return order;
    }

    private Order enrichForListView(Order order) {
        if (hasValidLineItems(order)) {
            order.recalculateTotals();
        }

        ensureDerivedFields(order);
        return order;
    }

    private void ensureDerivedFields(Order order) {

        if ((order.getCustomerSnapshot() == null || isBlank(order.getShippingAddress())) && !isBlank(order.getCustomerId())) {
            customerRepository.findById(order.getCustomerId()).ifPresent(customer -> mergeCustomerData(order, customer));
        }

        if (order.getOrderedAt() == null) {
            order.getStatusHistory().stream()
                    .map(Order.StatusHistoryEntry::getChangedAt)
                    .filter(java.util.Objects::nonNull)
                    .min(Comparator.naturalOrder())
                    .ifPresent(order::setOrderedAt);
        }

        if (isBlank(order.getStatus())) {
            order.getStatusHistory().stream()
                    .filter(entry -> !isBlank(entry.getStatus()))
                    .max(Comparator.comparing(Order.StatusHistoryEntry::getChangedAt, Comparator.nullsLast(Comparator.naturalOrder())))
                    .map(Order.StatusHistoryEntry::getStatus)
                    .ifPresent(order::setStatus);
        }
    }

    private void mergeCustomerData(Order order, Customer customer) {
        if (order.getCustomerSnapshot() == null) {
            Order.CustomerSnapshot snapshot = new Order.CustomerSnapshot();
            snapshot.setCustomerId(customer.getId());
            snapshot.setFullName(customer.getFullName());
            snapshot.setEmail(customer.getEmail());
            snapshot.setCity(customerCity(customer));
            order.setCustomerSnapshot(snapshot);
        }

        if (isBlank(order.getShippingAddress())) {
            order.setShippingAddress(formatShippingAddress(customer.getAddress()));
        }
    }

    private String customerCity(Customer customer) {
        Address address = customer.getAddress();
        return address == null ? null : address.getCity();
    }

    private String formatShippingAddress(Address address) {
        if (address == null) {
            return null;
        }

        String street = address.getStreet();
        String city = address.getCity();

        if (!isBlank(street) && !isBlank(city)) {
            return street + ", " + city;
        }
        if (!isBlank(street)) {
            return street;
        }
        if (!isBlank(city)) {
            return city;
        }
        return null;
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    private boolean hasValidLineItems(Order order) {
        return order.getLineItems().stream().allMatch(line -> line.getQuantity() > 0);
    }

    private String normalizeFilter(String value) {
        return isBlank(value) ? null : value.trim();
    }

    private BigDecimal parseMinTotal(String minTotal) {
        if (isBlank(minTotal)) {
            return null;
        }
        return new BigDecimal(minTotal.trim());
    }
}
