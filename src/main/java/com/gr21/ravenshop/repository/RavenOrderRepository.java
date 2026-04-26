package com.gr21.ravenshop.repository;

import com.gr21.ravenshop.model.Order;
import net.ravendb.client.documents.IDocumentStore;
import net.ravendb.client.documents.session.IDocumentSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RavenOrderRepository implements OrderRepository {

    private static final String ORDER_ID_PREFIX = "orders/";

    private final IDocumentStore documentStore;

    public RavenOrderRepository(IDocumentStore documentStore) {
        this.documentStore = documentStore;
    }

    @Override
    public Order save(Order order) {
        try (IDocumentSession session = documentStore.openSession(documentStore.getDatabase())) {
            session.store(order);
            session.saveChanges();
            return order;
        }
    }

    @Override
    public Optional<Order> findById(String id) {
        try (IDocumentSession session = documentStore.openSession(documentStore.getDatabase())) {
            return Optional.ofNullable(session.load(Order.class, id));
        }
    }

    @Override
    public List<Order> findAll() {
        try (IDocumentSession session = documentStore.openSession(documentStore.getDatabase())) {
            return session.advanced()
                    .rawQuery(Order.class, """
                            from @all_docs
                            where startsWith(id(), $idPrefix)
                            order by orderedAt desc
                            """)
                    .addParameter("idPrefix", ORDER_ID_PREFIX)
                    .toList();
        }
    }
}
