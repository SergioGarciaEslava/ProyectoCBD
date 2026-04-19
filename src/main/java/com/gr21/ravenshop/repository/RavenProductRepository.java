package com.gr21.ravenshop.repository;

import com.gr21.ravenshop.model.Product;
import net.ravendb.client.documents.IDocumentStore;
import net.ravendb.client.documents.session.IDocumentSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RavenProductRepository implements ProductRepository {

    private final IDocumentStore documentStore;

    public RavenProductRepository(IDocumentStore documentStore) {
        this.documentStore = documentStore;
    }

    @Override
    public Product save(Product product) {
        try (IDocumentSession session = documentStore.openSession(documentStore.getDatabase())) {
            session.store(product);
            session.saveChanges();
            return product;
        }
    }

    @Override
    public Optional<Product> findById(String id) {
        try (IDocumentSession session = documentStore.openSession(documentStore.getDatabase())) {
            return Optional.ofNullable(session.load(Product.class, id));
        }
    }

    @Override
    public List<Product> findAll() {
        try (IDocumentSession session = documentStore.openSession(documentStore.getDatabase())) {
            return session.query(Product.class).toList();
        }
    }
}
