package com.gr21.ravenshop.repository;

import com.gr21.ravenshop.model.Order;
import net.ravendb.client.documents.IDocumentStore;
import net.ravendb.client.documents.session.IDocumentSession;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RavenOrderRepository implements OrderRepository {

    private final IDocumentStore documentStore;

    public RavenOrderRepository(IDocumentStore documentStore) {
        this.documentStore = documentStore;
    }

    @Override
    public Optional<Order> findById(String id) {
        try (IDocumentSession session = documentStore.openSession(documentStore.getDatabase())) {
            return Optional.ofNullable(session.load(Order.class, id));
        }
    }
}
