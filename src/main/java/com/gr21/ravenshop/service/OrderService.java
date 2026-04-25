package com.gr21.ravenshop.service;

import com.gr21.ravenshop.model.Address;
import com.gr21.ravenshop.model.Customer;
import com.gr21.ravenshop.model.Order;
import com.gr21.ravenshop.repository.CustomerRepository;
import com.gr21.ravenshop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public Optional<Order> findById(String orderId) {
        return orderRepository.findById(normalizeId(orderId))
                .map(this::enrichForDetailView);
    }

    private String normalizeId(String orderId) {
        return orderId.contains("/") ? orderId : "orders/" + orderId;
    }

    private Order enrichForDetailView(Order order) {
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

        return order;
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
}
