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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrderCopiesSnapshotsCalculatesTotalsAndSavesPendingOrder() {
        Customer customer = new Customer();
        customer.setId("customers/1-A");
        customer.setFullName("Ana Lopez");
        customer.setEmail("ana.lopez@example.com");
        customer.setAddress(new Address("Calle Mayor 1", "Madrid", null));

        Product coffee = product("products/1-A", "Cafe Colombia", "Bebidas", "10.50");
        Product tea = product("products/2-A", "Te Verde", "Bebidas", "4.25");

        when(customerRepository.findById("customers/1-A")).thenReturn(Optional.of(customer));
        when(productRepository.findById("products/1-A")).thenReturn(Optional.of(coffee));
        when(productRepository.findById("products/2-A")).thenReturn(Optional.of(tea));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order order = invocation.getArgument(0);
            order.setId("orders/99-A");
            return order;
        });

        OrderCreateForm form = new OrderCreateForm();
        form.setCustomerId("customers/1-A");
        form.setShippingAddress("Calle Mayor 1, Madrid");
        form.setLines(List.of(lineForm("products/1-A", 2), lineForm("products/2-A", 3)));

        Order created = orderService.createOrder(form);

        assertThat(created.getId()).isEqualTo("orders/99-A");
        assertThat(created.getCustomerId()).isEqualTo("customers/1-A");
        assertThat(created.getCustomerSnapshot().getFullName()).isEqualTo("Ana Lopez");
        assertThat(created.getCustomerSnapshot().getEmail()).isEqualTo("ana.lopez@example.com");
        assertThat(created.getCustomerSnapshot().getCity()).isEqualTo("Madrid");
        assertThat(created.getStatus()).isEqualTo(Order.STATUS_PENDING);
        assertThat(created.getStatusHistory()).hasSize(1);
        assertThat(created.getOrderedAt()).isNotNull();
        assertThat(created.getLineItems()).hasSize(2);
        assertThat(created.getLineItems().get(0).getProductName()).isEqualTo("Cafe Colombia");
        assertThat(created.getLineItems().get(0).getCategory()).isEqualTo("Bebidas");
        assertThat(created.getLineItems().get(0).getLineTotal()).isEqualByComparingTo("21.00");
        assertThat(created.getLineItems().get(1).getLineTotal()).isEqualByComparingTo("12.75");
        assertThat(created.getTotal()).isEqualByComparingTo("33.75");

        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void listOrdersReturnsOrdersWithTotalsAndFallbackCustomerSnapshotData() {
        Order order = new Order();
        order.setId("orders/1-A");
        order.setCustomerId("customers/1-A");
        order.setLineItems(List.of(line("products/1-A", "Cafe", "Bebidas", 2, "21.90", "0.00")));
        order.setStatusHistory(List.of(history("Pending", "2026-04-19T10:00:00Z")));

        Customer customer = new Customer();
        customer.setId("customers/1-A");
        customer.setFullName("Ana Lopez");
        customer.setEmail("ana.lopez@example.com");
        customer.setAddress(new Address("Calle Mayor 1", "Madrid", null));

        when(orderRepository.findAll()).thenReturn(List.of(order));
        when(customerRepository.findById("customers/1-A")).thenReturn(Optional.of(customer));

        List<Order> result = orderService.listOrders();

        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getTotal()).isEqualByComparingTo("43.80");
        assertThat(result.getFirst().getOrderedAt()).isEqualTo(OffsetDateTime.parse("2026-04-19T10:00:00Z"));
        assertThat(result.getFirst().getStatus()).isEqualTo("Pending");
        assertThat(result.getFirst().getCustomerSnapshot()).isNotNull();
        assertThat(result.getFirst().getCustomerSnapshot().getFullName()).isEqualTo("Ana Lopez");

        verify(orderRepository).findAll();
        verify(customerRepository).findById("customers/1-A");
    }

    @Test
    void findByIdNormalizesIdAndCompletesLegacyFieldsFromCustomerAndHistory() {
        Order order = new Order();
        order.setId("orders/1-A");
        order.setCustomerId("customers/1-A");
        order.setLineItems(List.of(line("products/1-A", "Cafe", "Bebidas", 2, "21.90", "0.00")));
        order.setTotal(new BigDecimal("0.00"));
        order.setStatusHistory(List.of(
                history("CREATED", "2026-04-19T10:00:00Z"),
                history("PAID", "2026-04-19T10:05:00Z")
        ));

        Customer customer = new Customer();
        customer.setId("customers/1-A");
        customer.setFullName("Ana Lopez");
        customer.setEmail("ana.lopez@example.com");
        customer.setAddress(new Address("Calle Mayor 1", "Madrid", null));

        when(orderRepository.findById("orders/1-A")).thenReturn(Optional.of(order));
        when(customerRepository.findById("customers/1-A")).thenReturn(Optional.of(customer));

        Optional<Order> result = orderService.findById("1-A");

        assertThat(result).isPresent();
        assertThat(result.get().getCustomerSnapshot()).isNotNull();
        assertThat(result.get().getCustomerSnapshot().getFullName()).isEqualTo("Ana Lopez");
        assertThat(result.get().getShippingAddress()).isEqualTo("Calle Mayor 1, Madrid");
        assertThat(result.get().getOrderedAt()).isEqualTo(OffsetDateTime.parse("2026-04-19T10:00:00Z"));
        assertThat(result.get().getStatus()).isEqualTo("PAID");
        assertThat(result.get().getLineItems().get(0).getLineTotal()).isEqualByComparingTo("43.80");
        assertThat(result.get().getTotal()).isEqualByComparingTo("43.80");

        verify(orderRepository).findById("orders/1-A");
        verify(customerRepository).findById("customers/1-A");
    }

    @Test
    void findByIdReturnsEmptyWhenOrderDoesNotExist() {
        when(orderRepository.findById("orders/404-A")).thenReturn(Optional.empty());

        Optional<Order> result = orderService.findById("404-A");

        assertThat(result).isEmpty();
        verify(orderRepository).findById("orders/404-A");
    }

    @Test
    void listOrdersSkipsTotalRecalculationWhenAStoredLineHasInvalidQuantity() {
        Order order = new Order();
        order.setId("orders/2-A");
        order.setStatus("Pending");
        order.setTotal(new BigDecimal("15.00"));
        order.setLineItems(List.of(line("products/1-A", "Cafe", "Bebidas", 0, "21.90", "0.00")));

        when(orderRepository.findAll()).thenReturn(List.of(order));

        List<Order> result = orderService.listOrders();

        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getTotal()).isEqualByComparingTo("15.00");
        verify(orderRepository).findAll();
    }

    @Test
    void createOrderRejectsMissingCustomer() {
        OrderCreateForm form = new OrderCreateForm();
        form.setLines(List.of(lineForm("products/1-A", 1)));

        assertThatThrownBy(() -> orderService.createOrder(form))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cliente no encontrado");
    }

    @Test
    void findByIdDetectsInvalidQuantitiesWhenRecalculatingTotalsOnServer() {
        Order order = new Order();
        order.setId("orders/2-A");
        order.setLineItems(List.of(line("products/1-A", "Cafe", "Bebidas", 0, "21.90", "0.00")));

        when(orderRepository.findById("orders/2-A")).thenReturn(Optional.of(order));

        assertThatThrownBy(() -> orderService.findById("2-A"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Order.INVALID_QUANTITY_MESSAGE);

        verify(orderRepository).findById("orders/2-A");
    }

    private Order.OrderLineItem line(String productId, String productName, String category, int quantity, String unitPrice,
            String lineTotal) {
        Order.OrderLineItem line = new Order.OrderLineItem();
        line.setProductId(productId);
        line.setProductName(productName);
        line.setCategory(category);
        line.setQuantity(quantity);
        line.setUnitPrice(new BigDecimal(unitPrice));
        line.setLineTotal(new BigDecimal(lineTotal));
        return line;
    }

    private OrderLineForm lineForm(String productId, int quantity) {
        OrderLineForm line = new OrderLineForm();
        line.setProductId(productId);
        line.setQuantity(quantity);
        return line;
    }

    private Product product(String id, String name, String category, String price) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setCategory(category);
        product.setPrice(new BigDecimal(price));
        return product;
    }

    private Order.StatusHistoryEntry history(String status, String changedAt) {
        Order.StatusHistoryEntry entry = new Order.StatusHistoryEntry();
        entry.setStatus(status);
        entry.setChangedAt(OffsetDateTime.parse(changedAt));
        return entry;
    }
}
