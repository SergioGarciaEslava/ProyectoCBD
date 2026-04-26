package com.gr21.ravenshop.seed;

import com.gr21.ravenshop.model.Address;
import com.gr21.ravenshop.model.Customer;
import com.gr21.ravenshop.model.Order;
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
        session.store(customer("Ana Lopez", "ana.lopez@example.com", "+34 600000001", "Calle Mayor 1", "Madrid", "28013"), "customers/1-A");
        session.store(customer("Bruno Diaz", "bruno.diaz@example.com", "+34 600000002", "Avenida Colon 12", "Valencia", "46004"), "customers/2-A");
        session.store(customer("Carla Martin", "carla.martin@example.com", "+34 600000003", "Calle Feria 8", "Sevilla", "41003"), "customers/3-A");
    }

    private Customer customer(String fullName, String email, String phone, String street, String city, String postalCode) {
        Customer customer = new Customer();
        customer.setFullName(fullName);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(new Address(street, city, postalCode));
        customer.setCreatedAt(OffsetDateTime.now());
        return customer;
    }

    private void storeOrders(IDocumentSession session) {
        session.store(order(
                "customers/1-A",
                customerSnapshot("customers/1-A", "Ana Lopez", "ana.lopez@example.com", "Madrid"),
                "Calle Mayor 1, Madrid",
                "Paid",
                "2026-04-19T10:00:00Z",
                List.of(
                        orderLine("products/1-A", "Cafe de especialidad 1kg", "Bebidas", 2, "21.90"),
                        orderLine("products/3-A", "Chocolate negro 70% 200g", "Snacks", 3, "6.95")
                ),
                List.of(
                        statusEntry("Pending", "2026-04-19T10:00:00Z", "Pedido creado"),
                        statusEntry("Paid", "2026-04-19T10:05:00Z", "Pago confirmado")
                )
        ), "orders/1-A");

        session.store(order(
                "customers/2-A",
                customerSnapshot("customers/2-A", "Bruno Diaz", "bruno.diaz@example.com", "Valencia"),
                "Avenida Colon 12, Valencia",
                "Processing",
                "2026-04-19T11:00:00Z",
                List.of(
                        orderLine("products/2-A", "Te verde organico 500g", "Bebidas", 2, "12.40"),
                        orderLine("products/4-A", "Muesli sin azucar 750g", "Despensa", 1, "8.50")
                ),
                List.of(
                        statusEntry("Pending", "2026-04-19T11:00:00Z", "Pedido creado"),
                        statusEntry("Processing", "2026-04-19T11:20:00Z", "Preparando pedido")
                )
        ), "orders/2-A");

        session.store(order(
                "customers/3-A",
                customerSnapshot("customers/3-A", "Carla Martin", "carla.martin@example.com", "Sevilla"),
                "Calle Feria 8, Sevilla",
                "Shipped",
                "2026-04-19T12:00:00Z",
                List.of(
                        orderLine("products/1-A", "Cafe de especialidad 1kg", "Bebidas", 1, "21.90"),
                        orderLine("products/2-A", "Te verde organico 500g", "Bebidas", 1, "12.40"),
                        orderLine("products/3-A", "Chocolate negro 70% 200g", "Snacks", 2, "6.95")
                ),
                List.of(
                        statusEntry("Pending", "2026-04-19T12:00:00Z", "Pedido creado"),
                        statusEntry("Paid", "2026-04-19T12:10:00Z", "Pago confirmado"),
                        statusEntry("Shipped", "2026-04-19T13:00:00Z", "Pedido enviado")
                )
        ), "orders/3-A");

        session.store(order(
                "customers/1-A",
                customerSnapshot("customers/1-A", "Ana Lopez", "ana.lopez@example.com", "Madrid"),
                "Calle Mayor 1, Madrid",
                "Pending",
                "2026-04-19T14:00:00Z",
                List.of(
                        orderLine("products/4-A", "Muesli sin azucar 750g", "Despensa", 2, "8.50")
                ),
                List.of(
                        statusEntry("Pending", "2026-04-19T14:00:00Z", "Pedido creado")
                )
        ), "orders/4-A");
    }

    private Order order(
            String customerId,
            Order.CustomerSnapshot customerSnapshot,
            String shippingAddress,
            String status,
            String orderedAt,
            List<Order.OrderLineItem> lineItems,
            List<Order.StatusHistoryEntry> statusHistory
    ) {
        Order order = new Order();
        order.setCustomerId(customerId);
        order.setCustomerSnapshot(customerSnapshot);
        order.setShippingAddress(shippingAddress);
        order.setStatus(status);
        order.setOrderedAt(OffsetDateTime.parse(orderedAt));
        order.setLineItems(lineItems);
        order.setStatusHistory(statusHistory);
        order.recalculateTotals();
        return order;
    }

    private Order.CustomerSnapshot customerSnapshot(String customerId, String fullName, String email, String city) {
        Order.CustomerSnapshot snapshot = new Order.CustomerSnapshot();
        snapshot.setCustomerId(customerId);
        snapshot.setFullName(fullName);
        snapshot.setEmail(email);
        snapshot.setCity(city);
        return snapshot;
    }

    private Order.OrderLineItem orderLine(String productId, String productName, String category, int quantity, String unitPrice) {
        Order.OrderLineItem lineItem = new Order.OrderLineItem();
        lineItem.setProductId(productId);
        lineItem.setProductName(productName);
        lineItem.setCategory(category);
        lineItem.setQuantity(quantity);
        lineItem.setUnitPrice(new BigDecimal(unitPrice));
        return lineItem;
    }

    private Order.StatusHistoryEntry statusEntry(String status, String changedAt, String comment) {
        return new Order.StatusHistoryEntry(status, OffsetDateTime.parse(changedAt), comment);
    }

    public record SeedMarker(String id, String seededAt) {
    }
}
