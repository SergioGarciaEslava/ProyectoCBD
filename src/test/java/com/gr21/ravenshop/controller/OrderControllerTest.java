package com.gr21.ravenshop.controller;

import com.gr21.ravenshop.model.Order;
import com.gr21.ravenshop.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @Test
    void detailViewRendersOrderFields() throws Exception {
        Order order = new Order();
        order.setId("orders/1-A");
        order.setOrderedAt(OffsetDateTime.parse("2026-04-19T10:00:00Z"));
        order.setStatus("PAID");
        order.setShippingAddress("Calle Mayor 1, Madrid");
        order.setTotal(new BigDecimal("64.65"));

        Order.CustomerSnapshot snapshot = new Order.CustomerSnapshot();
        snapshot.setCustomerId("customers/1-A");
        snapshot.setFullName("Ana Lopez");
        snapshot.setEmail("ana.lopez@example.com");
        snapshot.setCity("Madrid");
        order.setCustomerSnapshot(snapshot);

        Order.OrderLineItem line = new Order.OrderLineItem();
        line.setProductId("products/1-A");
        line.setProductName("Cafe de especialidad 1kg");
        line.setCategory("Bebidas");
        line.setQuantity(2);
        line.setUnitPrice(new BigDecimal("21.90"));
        line.setLineTotal(new BigDecimal("43.80"));
        order.setLineItems(List.of(line));

        Order.StatusHistoryEntry entry = new Order.StatusHistoryEntry();
        entry.setStatus("PAID");
        entry.setChangedAt(OffsetDateTime.parse("2026-04-19T10:05:00Z"));
        order.setStatusHistory(List.of(entry));

        given(orderService.findById("1-A")).willReturn(Optional.of(order));

        mockMvc.perform(get("/orders/1-A"))
                .andExpect(status().isOk())
                .andExpect(view().name("orders/detail"))
                .andExpect(model().attributeExists("order"))
                .andExpect(content().string(containsString("orders/1-A")))
                .andExpect(content().string(containsString("Ana Lopez")))
                .andExpect(content().string(containsString("Calle Mayor 1, Madrid")))
                .andExpect(content().string(containsString("Cafe de especialidad 1kg")))
                .andExpect(content().string(containsString("64.65")))
                .andExpect(content().string(containsString("PAID")));
    }

    @Test
    void detailViewReturns404WhenOrderDoesNotExist() throws Exception {
        given(orderService.findById("404-A")).willReturn(Optional.empty());

        mockMvc.perform(get("/orders/404-A"))
                .andExpect(status().isNotFound());
    }
}
