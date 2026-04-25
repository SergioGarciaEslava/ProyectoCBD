package com.gr21.ravenshop.repository;

import com.gr21.ravenshop.model.Product;
import net.ravendb.client.documents.IDocumentStore;
import net.ravendb.client.documents.session.IDocumentSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RavenProductRepository implements ProductRepository {

    private static final String PRODUCT_ID_PREFIX = "products/";

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
            return session.advanced()
                    .rawQuery(Product.class, "from @all_docs where startsWith(id(), $idPrefix)")
                    .addParameter("idPrefix", PRODUCT_ID_PREFIX)
                    .toList();
        }
    }

    @Override
    public List<Product> searchByNamePrefix(String namePrefix) {
        try (IDocumentSession session = documentStore.openSession(documentStore.getDatabase())) {
            return session.advanced()
                    .rawQuery(Product.class, "from Products where startsWith(Name, $namePrefix)")
                    .addParameter("namePrefix", namePrefix)
                    .toList();
        }
    }

    @Override
    public boolean deleteById(String id) {
        try (IDocumentSession session = documentStore.openSession(documentStore.getDatabase())) {
            Product product = session.load(Product.class, id);
            if (product == null) {
                return false;
            }

            session.delete(product);
            session.saveChanges();
            return true;
        }
    }
}
