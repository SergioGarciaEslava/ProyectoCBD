package com.gr21.ravenshop.repository;

import com.gr21.ravenshop.model.Customer;
import net.ravendb.client.documents.IDocumentStore;
import net.ravendb.client.documents.session.IDocumentSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RavenCustomerRepository implements CustomerRepository {

    private final IDocumentStore documentStore;

    public RavenCustomerRepository(IDocumentStore documentStore) {
        this.documentStore = documentStore;
    }

    @Override
    public Customer save(Customer customer) {
        try (IDocumentSession session = documentStore.openSession(documentStore.getDatabase())) {
            session.store(customer);
            session.saveChanges();
            return customer;
        }
    }

    @Override
    public Optional<Customer> findById(String id) {
        try (IDocumentSession session = documentStore.openSession(documentStore.getDatabase())) {
            return Optional.ofNullable(session.load(Customer.class, id));
        }
    }

    @Override
    public List<Customer> findAll() {
        try (IDocumentSession session = documentStore.openSession(documentStore.getDatabase())) {
            return session.query(Customer.class).toList();
        }
    }
}
