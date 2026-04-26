package com.gr21.ravenshop.repository;

import com.gr21.ravenshop.model.Order;
import net.ravendb.client.documents.IDocumentStore;
import net.ravendb.client.documents.session.IDocumentSession;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        return findByFilters(null, null, null);
    }

    @Override
    public List<Order> findByFilters(String status, String customer, BigDecimal minTotal) {
        try (IDocumentSession session = documentStore.openSession(documentStore.getDatabase())) {
            List<String> conditions = new ArrayList<>();
            Map<String, Object> parameters = new LinkedHashMap<>();

            conditions.add("startsWith(id(), $idPrefix)");
            parameters.put("idPrefix", ORDER_ID_PREFIX);

            if (hasText(status)) {
                conditions.add("status = $status");
                parameters.put("status", status);
            }

            if (hasText(customer)) {
                conditions.add("customerSnapshot.fullName = $customer");
                parameters.put("customer", customer);
            }

            if (minTotal != null) {
                conditions.add("total >= $minTotal");
                parameters.put("minTotal", minTotal);
            }

            String rql = "from @all_docs where " + String.join(" and ", conditions) + " order by orderedAt desc";

            var query = session.advanced().rawQuery(Order.class, rql);
            for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
                query.addParameter(parameter.getKey(), parameter.getValue());
            }
            return query.toList();
        }
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }
}
