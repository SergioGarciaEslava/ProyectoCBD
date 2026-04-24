package com.gr21.ravenshop.service;

import com.gr21.ravenshop.model.Customer;
import com.gr21.ravenshop.model.Order;
import com.gr21.ravenshop.repository.CustomerRepository;
import com.gr21.ravenshop.repository.OrderRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void findByIdNormalizesIdAndCompletesLegacyFieldsFromCustomerAndHistory() {
        Order order = new Order();
        order.setId("orders/1-A");
        order.setCustomerId("customers/1-A");
        order.setLineItems(List.of(line("products/1-A", "Cafe", 2, "21.90")));
        order.setTotal(new BigDecimal("43.80"));
        order.setStatusHistory(List.of(
                history("CREATED", "2026-04-19T10:00:00Z"),
                history("PAID", "2026-04-19T10:05:00Z")
        ));

        Customer customer = new Customer();
        customer.setId("customers/1-A");
        customer.setFullName("Ana Lopez");
        customer.setEmail("ana.lopez@example.com");
        customer.setCity("Madrid");
        customer.setAddress("Calle Mayor 1");

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

    private Order.OrderLineItem line(String productId, String productName, int quantity, String unitPrice) {
        Order.OrderLineItem line = new Order.OrderLineItem();
        line.setProductId(productId);
        line.setProductName(productName);
        line.setQuantity(quantity);
        line.setUnitPrice(new BigDecimal(unitPrice));
        return line;
    }

    private Order.StatusHistoryEntry history(String status, String changedAt) {
        Order.StatusHistoryEntry entry = new Order.StatusHistoryEntry();
        entry.setStatus(status);
        entry.setChangedAt(OffsetDateTime.parse(changedAt));
        return entry;
    }
}
