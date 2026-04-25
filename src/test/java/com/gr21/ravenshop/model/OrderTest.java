package com.gr21.ravenshop.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {

    @Test
    void createPendingInitializesCurrentStatusAndFirstHistoryEntry() {
        Order order = Order.createPending();

        assertThat(order.getStatus()).isEqualTo(Order.STATUS_PENDING);
        assertThat(order.getStatusHistory()).hasSize(1);

        Order.StatusHistoryEntry initialEntry = order.getStatusHistory().get(0);
        assertThat(initialEntry.getStatus()).isEqualTo(Order.STATUS_PENDING);
        assertThat(initialEntry.getChangedAt()).isNotNull();
        assertThat(initialEntry.getComment()).isEqualTo(Order.INITIAL_STATUS_COMMENT);
    }

    @Test
    void createPendingRejectsEmptyOrders() {
        assertThatThrownBy(() -> Order.createPending(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("El pedido debe tener al menos una linea");
    }

    @Test
    void createPendingRejectsLineItemsWithoutPositiveQuantity() {
        assertThatThrownBy(() -> Order.createPending(List.of(line(0, "10.50"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("La cantidad de cada linea debe ser mayor que cero");
    }

    @Test
    void createPendingInitializesPendingStatusAndRecalculatesTotals() {
        Order order = Order.createPending(List.of(
                line(2, "10.50"),
                line(3, "4.25")
        ));

        assertThat(order.getStatus()).isEqualTo(Order.STATUS_PENDING);
        assertThat(order.getStatusHistory()).hasSize(1);
        assertThat(order.getLineItems().get(0).getLineTotal()).isEqualByComparingTo("21.00");
        assertThat(order.getLineItems().get(1).getLineTotal()).isEqualByComparingTo("12.75");
        assertThat(order.getTotal()).isEqualByComparingTo("33.75");
    }

    @Test
    void recalculateTotalsCalculatesLineTotalsAndOrderTotalFromQuantityAndUnitPrice() {
        Order order = new Order();
        order.setLineItems(List.of(
                line(2, "10.50"),
                line(3, "4.25")
        ));

        order.recalculateTotals();

        assertThat(order.getLineItems().get(0).getLineTotal()).isEqualByComparingTo("21.00");
        assertThat(order.getLineItems().get(1).getLineTotal()).isEqualByComparingTo("12.75");
        assertThat(order.getTotal()).isEqualByComparingTo("33.75");
    }

    private Order.OrderLineItem line(int quantity, String unitPrice) {
        Order.OrderLineItem line = new Order.OrderLineItem();
        line.setQuantity(quantity);
        line.setUnitPrice(new BigDecimal(unitPrice));
        return line;
    }
}
