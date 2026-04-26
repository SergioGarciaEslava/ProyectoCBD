package com.gr21.ravenshop.repository;

import com.gr21.ravenshop.model.Order;
import net.ravendb.client.documents.IDocumentStore;
import net.ravendb.client.documents.session.IDocumentSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RavenOrderRepositoryTest {

    @Mock
    private IDocumentStore documentStore;

    @Mock
    private IDocumentSession session;

    private RavenOrderRepository repository;

    @BeforeEach
    void setUp() {
        repository = new RavenOrderRepository(documentStore);
        when(documentStore.getDatabase()).thenReturn("RavenShop");
        when(documentStore.openSession("RavenShop")).thenReturn(session);
    }

    @Test
    void saveAssignsExplicitOrderIdForNewDocuments() {
        Order order = new Order();

        Order saved = repository.save(order);

        ArgumentCaptor<String> idCaptor = ArgumentCaptor.forClass(String.class);
        verify(session).store(eq(order), idCaptor.capture());
        verify(session).saveChanges();

        assertThat(saved.getId()).startsWith("orders/");
        assertThat(idCaptor.getValue()).isEqualTo(saved.getId());
    }
}
