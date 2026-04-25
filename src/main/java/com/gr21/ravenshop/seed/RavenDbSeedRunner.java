package com.gr21.ravenshop.seed;

import com.gr21.ravenshop.model.Product;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import net.ravendb.client.documents.IDocumentStore;
import net.ravendb.client.documents.session.IDocumentSession;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "ravenshop.seed", name = "enabled", havingValue = "true")
public class RavenDbSeedRunner implements ApplicationRunner {

    static final String SEED_MARKER_ID = "seed-data/ravenshop-wi020";

    private final IDocumentStore documentStore;

    public RavenDbSeedRunner(IDocumentStore documentStore) {
        this.documentStore = documentStore;
    }

    @Override
    public void run(ApplicationArguments args) {
        try (IDocumentSession session = documentStore.openSession(documentStore.getDatabase())) {
            SeedMarker marker = session.load(SeedMarker.class, SEED_MARKER_ID);
            if (marker != null) {
                return;
            }

            storeProducts(session);
            storeCustomers(session);
            storeOrders(session);
            session.store(new SeedMarker(SEED_MARKER_ID, OffsetDateTime.now().toString()), SEED_MARKER_ID);
            session.saveChanges();
        }
    }

    private void storeProducts(IDocumentSession session) {
        session.store(product("Cafe de especialidad 1kg", "Bebidas", "21.90", 50), "products/1-A");
        session.store(product("Te verde organico 500g", "Bebidas", "12.40", 80), "products/2-A");
        session.store(product("Chocolate negro 70% 200g", "Snacks", "6.95", 120), "products/3-A");
        session.store(product("Muesli sin azucar 750g", "Despensa", "8.50", 65), "products/4-A");
        session.store(product("Cafe instantaneo 200g", "Bebidas", "5.40", 100), "products/5-A");
        session.store(product("Cafe descafeinado 250g", "Bebidas", "7.80", 70), "products/6-A");
    }

    private Product product(String name, String category, String price, int stock) {
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setPrice(new BigDecimal(price));
        product.setStock(stock);
        product.setCreatedAt(OffsetDateTime.now());
        return product;
    }

    private void storeCustomers(IDocumentSession session) {
        session.store(new CustomerDoc("customers/1-A", "Ana Lopez", "ana.lopez@example.com", "Madrid"), "customers/1-A");
        session.store(new CustomerDoc("customers/2-A", "Bruno Diaz", "bruno.diaz@example.com", "Valencia"), "customers/2-A");
        session.store(new CustomerDoc("customers/3-A", "Carla Martin", "carla.martin@example.com", "Sevilla"), "customers/3-A");
    }

    private void storeOrders(IDocumentSession session) {
        session.store(new OrderDoc(
                "orders/1-A",
                "customers/1-A",
                List.of(
                        new OrderLine("products/1-A", "Cafe de especialidad 1kg", 2, new BigDecimal("21.90")),
                        new OrderLine("products/3-A", "Chocolate negro 70% 200g", 3, new BigDecimal("6.95"))
                ),
                new BigDecimal("64.65"),
                List.of(
                        new OrderStatusEntry("CREATED", "2026-04-19T10:00:00Z"),
                        new OrderStatusEntry("PAID", "2026-04-19T10:05:00Z")
                )
        ), "orders/1-A");

        session.store(new OrderDoc(
                "orders/2-A",
                "customers/2-A",
                List.of(
                        new OrderLine("products/2-A", "Te verde organico 500g", 2, new BigDecimal("12.40")),
                        new OrderLine("products/4-A", "Muesli sin azucar 750g", 1, new BigDecimal("8.50"))
                ),
                new BigDecimal("33.30"),
                List.of(
                        new OrderStatusEntry("CREATED", "2026-04-19T11:00:00Z"),
                        new OrderStatusEntry("PROCESSING", "2026-04-19T11:20:00Z")
                )
        ), "orders/2-A");

        session.store(new OrderDoc(
                "orders/3-A",
                "customers/3-A",
                List.of(
                        new OrderLine("products/1-A", "Cafe de especialidad 1kg", 1, new BigDecimal("21.90")),
                        new OrderLine("products/2-A", "Te verde organico 500g", 1, new BigDecimal("12.40")),
                        new OrderLine("products/3-A", "Chocolate negro 70% 200g", 2, new BigDecimal("6.95"))
                ),
                new BigDecimal("48.20"),
                List.of(
                        new OrderStatusEntry("CREATED", "2026-04-19T12:00:00Z"),
                        new OrderStatusEntry("PAID", "2026-04-19T12:10:00Z"),
                        new OrderStatusEntry("SHIPPED", "2026-04-19T13:00:00Z")
                )
        ), "orders/3-A");

        session.store(new OrderDoc(
                "orders/4-A",
                "customers/1-A",
                List.of(
                        new OrderLine("products/4-A", "Muesli sin azucar 750g", 2, new BigDecimal("8.50"))
                ),
                new BigDecimal("17.00"),
                List.of(
                        new OrderStatusEntry("CREATED", "2026-04-19T14:00:00Z")
                )
        ), "orders/4-A");
    }

    public record SeedMarker(String id, String seededAt) {
    }

    public record CustomerDoc(String id, String fullName, String email, String city) {
    }

    public record OrderDoc(String id, String customerId, List<OrderLine> lines, BigDecimal total, List<OrderStatusEntry> statusHistory) {
    }

    public record OrderLine(String productId, String productName, int quantity, BigDecimal unitPrice) {
    }

    public record OrderStatusEntry(String status, String changedAt) {
    }
}
