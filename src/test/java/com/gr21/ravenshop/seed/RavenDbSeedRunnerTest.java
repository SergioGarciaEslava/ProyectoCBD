package com.gr21.ravenshop.seed;

import com.gr21.ravenshop.model.Customer;
import com.gr21.ravenshop.model.Order;
import com.gr21.ravenshop.model.Product;
import net.ravendb.client.documents.IDocumentStore;
import net.ravendb.client.documents.session.IDocumentSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.ApplicationArguments;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RavenDbSeedRunnerTest {

    private static final String SEED_MARKER_ID = RavenDbSeedRunner.SEED_MARKER_ID;
    private static final int EXPECTED_PRODUCT_COUNT = 78;
    private static final int EXPECTED_CUSTOMER_COUNT = 192;
    private static final int EXPECTED_ORDER_COUNT = 820;
    private static final int EXPECTED_TOTAL_STORES =
            EXPECTED_PRODUCT_COUNT + EXPECTED_CUSTOMER_COUNT + EXPECTED_ORDER_COUNT + 1;

    @Mock
    private IDocumentStore documentStore;

    @Mock
    private IDocumentSession session;

    @Mock
    private ApplicationArguments args;

    private RavenDbSeedRunner runner;

    @BeforeEach
    void setUp() {
        runner = new RavenDbSeedRunner(documentStore);
        when(documentStore.getDatabase()).thenReturn("RavenShop");
        when(documentStore.openSession("RavenShop")).thenReturn(session);
    }

    @Test
    void shouldSeedDataWhenMarkerDoesNotExist() throws Exception {
        when(session.load(RavenDbSeedRunner.SeedMarker.class, SEED_MARKER_ID)).thenReturn(null);

        runner.run(args);

        ArgumentCaptor<Object> storedEntities = ArgumentCaptor.forClass(Object.class);
        verify(session, times(EXPECTED_TOTAL_STORES)).store(any(), anyString());
        verify(session, times(EXPECTED_TOTAL_STORES)).store(storedEntities.capture(), anyString());
        verify(session, times(1)).saveChanges();

        List<Object> allStored = storedEntities.getAllValues();
        org.assertj.core.api.Assertions.assertThat(allStored.subList(0, EXPECTED_PRODUCT_COUNT))
                .allMatch(Product.class::isInstance);
        org.assertj.core.api.Assertions.assertThat(allStored.subList(EXPECTED_PRODUCT_COUNT, EXPECTED_PRODUCT_COUNT + EXPECTED_CUSTOMER_COUNT))
                .allMatch(Customer.class::isInstance);
        org.assertj.core.api.Assertions.assertThat(allStored.subList(EXPECTED_PRODUCT_COUNT + EXPECTED_CUSTOMER_COUNT, EXPECTED_PRODUCT_COUNT + EXPECTED_CUSTOMER_COUNT + EXPECTED_ORDER_COUNT))
                .allMatch(Order.class::isInstance);

        List<Order> allOrders = allStored.subList(
                EXPECTED_PRODUCT_COUNT + EXPECTED_CUSTOMER_COUNT,
                EXPECTED_PRODUCT_COUNT + EXPECTED_CUSTOMER_COUNT + EXPECTED_ORDER_COUNT
        ).stream().map(Order.class::cast).toList();

        org.assertj.core.api.Assertions.assertThat(allOrders)
                .anyMatch(order ->
                        order.getTotal() != null
                                && order.getTotal().compareTo(new java.math.BigDecimal("100.00")) >= 0
                                && order.getCustomerSnapshot() != null
                                && "Madrid".equals(order.getCustomerSnapshot().getCity())
                );
    }

    @Test
    void shouldSkipSeedWhenMarkerAlreadyExists() throws Exception {
        when(session.load(RavenDbSeedRunner.SeedMarker.class, SEED_MARKER_ID))
                .thenReturn(new RavenDbSeedRunner.SeedMarker(SEED_MARKER_ID, "2026-04-19"));

        runner.run(args);

        verify(session, never()).store(any(), anyString());
        verify(session, never()).saveChanges();
    }
}
