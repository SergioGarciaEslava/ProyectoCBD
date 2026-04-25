package com.gr21.ravenshop.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}
