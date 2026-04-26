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

    static final String SEED_MARKER_ID = "seed-data/ravenshop-wi021";

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
        session.store(product("Cafe de especialidad 1kg", "Bebidas", "21.90", 50,
                List.of("cafe", "premium", "grano"), "2026-02-01T09:00:00Z"), "products/1-A");
        session.store(product("Te verde organico 500g", "Bebidas", "12.40", 80,
                List.of("te", "organico", "bienestar"), "2026-02-03T09:30:00Z"), "products/2-A");
        session.store(product("Chocolate negro 70% 200g", "Snacks", "6.95", 120,
                List.of("chocolate", "snack", "intenso"), "2026-02-05T10:00:00Z"), "products/3-A");
        session.store(product("Muesli sin azucar 750g", "Despensa", "8.50", 65,
                List.of("desayuno", "saludable", "fibra"), "2026-02-08T08:15:00Z"), "products/4-A");
        session.store(product("Cafe instantaneo 200g", "Bebidas", "5.40", 100,
                List.of("cafe", "rapido", "oficina"), "2026-02-11T11:45:00Z"), "products/5-A");
        session.store(product("Cafe descafeinado 250g", "Bebidas", "7.80", 70,
                List.of("cafe", "descafeinado", "noche"), "2026-02-14T12:00:00Z"), "products/6-A");
        session.store(product("Infusion de frutos rojos 20u", "Bebidas", "4.90", 90,
                List.of("infusion", "frutos-rojos", "temporada"), "2026-02-16T10:20:00Z"), "products/7-A");
        session.store(product("Galletas integrales 300g", "Snacks", "3.85", 140,
                List.of("galletas", "integral", "snack"), "2026-02-18T16:10:00Z"), "products/8-A");
        session.store(product("Granola con frutos secos 500g", "Despensa", "9.75", 55,
                List.of("granola", "desayuno", "frutos-secos"), "2026-02-20T08:50:00Z"), "products/9-A");
        session.store(product("Crema de cacahuete 350g", "Despensa", "6.60", 40,
                List.of("proteina", "untable", "fitness"), "2026-02-24T13:25:00Z"), "products/10-A");
        session.store(product("Matcha ceremonial 100g", "Bebidas", "18.90", 25,
                List.of("matcha", "premium", "ceremonial"), "2026-03-01T09:10:00Z"), "products/11-A");
        session.store(product("Barritas de avena 6u", "Snacks", "4.50", 110,
                List.of("avena", "snack", "energia"), "2026-03-04T17:40:00Z"), "products/12-A");
    }

    private Product product(String name, String category, String price, int stock) {
        return product(name, category, price, stock, List.of(), OffsetDateTime.now().toString());
    }

    private Product product(String name, String category, String price, int stock, List<String> tags, String createdAt) {
        Product product = new Product();
        product.setName(name);
        product.setCategory(category);
        product.setPrice(new BigDecimal(price));
        product.setStock(stock);
        product.setTags(tags);
        product.setCreatedAt(OffsetDateTime.parse(createdAt));
        return product;
    }

    private void storeCustomers(IDocumentSession session) {
        session.store(customer("Ana Lopez", "ana.lopez@example.com", "+34 600000001",
                "Calle Mayor 1", "Madrid", "28013", "2026-02-10T10:00:00Z"), "customers/1-A");
        session.store(customer("Bruno Diaz", "bruno.diaz@example.com", "+34 600000002",
                "Avenida Colon 12", "Valencia", "46004", "2026-02-12T11:30:00Z"), "customers/2-A");
        session.store(customer("Carla Martin", "carla.martin@example.com", "+34 600000003",
                "Calle Feria 8", "Sevilla", "41003", "2026-02-15T09:45:00Z"), "customers/3-A");
        session.store(customer("Diego Perez", "diego.perez@example.com", "+34 600000004",
                "Calle Larios 18", "Malaga", "29005", "2026-02-18T15:20:00Z"), "customers/4-A");
        session.store(customer("Elena Soto", "elena.soto@example.com", "+34 600000005",
                "Paseo de Gracia 77", "Barcelona", "08008", "2026-02-22T12:40:00Z"), "customers/5-A");
        session.store(customer("Fatima Romero", "fatima.romero@example.com", "+34 600000006",
                "Calle San Juan 4", "Granada", "18001", "2026-02-25T18:05:00Z"), "customers/6-A");
        session.store(customer("Gabriel Ruiz", "gabriel.ruiz@example.com", "+34 600000007",
                "Rua do Franco 9", "Santiago de Compostela", "15702", "2026-03-02T08:25:00Z"), "customers/7-A");
        session.store(customer("Helena Torres", "helena.torres@example.com", "+34 600000008",
                "Calle Alfonso I 22", "Zaragoza", "50003", "2026-03-05T14:10:00Z"), "customers/8-A");
    }

    private Customer customer(String fullName, String email, String phone, String street, String city, String postalCode) {
        return customer(fullName, email, phone, street, city, postalCode, OffsetDateTime.now().toString());
    }

    private Customer customer(
            String fullName,
            String email,
            String phone,
            String street,
            String city,
            String postalCode,
            String createdAt
    ) {
        Customer customer = new Customer();
        customer.setFullName(fullName);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(new Address(street, city, postalCode));
        customer.setCreatedAt(OffsetDateTime.parse(createdAt));
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

        session.store(order(
                "customers/4-A",
                customerSnapshot("customers/4-A", "Diego Perez", "diego.perez@example.com", "Malaga"),
                "Calle Larios 18, Malaga",
                "Delivered",
                "2026-04-20T09:15:00Z",
                List.of(
                        orderLine("products/10-A", "Crema de cacahuete 350g", "Despensa", 2, "6.60"),
                        orderLine("products/12-A", "Barritas de avena 6u", "Snacks", 4, "4.50")
                ),
                List.of(
                        statusEntry("Pending", "2026-04-20T09:15:00Z", "Pedido creado"),
                        statusEntry("Paid", "2026-04-20T09:18:00Z", "Pago confirmado"),
                        statusEntry("Processing", "2026-04-20T10:00:00Z", "Preparando pedido"),
                        statusEntry("Shipped", "2026-04-20T16:30:00Z", "Pedido enviado"),
                        statusEntry("Delivered", "2026-04-21T12:10:00Z", "Entregado al cliente")
                )
        ), "orders/5-A");

        session.store(order(
                "customers/5-A",
                customerSnapshot("customers/5-A", "Elena Soto", "elena.soto@example.com", "Barcelona"),
                "Paseo de Gracia 77, Barcelona",
                "Cancelled",
                "2026-04-20T11:45:00Z",
                List.of(
                        orderLine("products/11-A", "Matcha ceremonial 100g", "Bebidas", 1, "18.90"),
                        orderLine("products/3-A", "Chocolate negro 70% 200g", "Snacks", 2, "6.95")
                ),
                List.of(
                        statusEntry("Pending", "2026-04-20T11:45:00Z", "Pedido creado"),
                        statusEntry("Paid", "2026-04-20T11:49:00Z", "Pago confirmado"),
                        statusEntry("Cancelled", "2026-04-20T13:05:00Z", "Cancelado por falta de stock del matcha")
                )
        ), "orders/6-A");

        session.store(order(
                "customers/6-A",
                customerSnapshot("customers/6-A", "Fatima Romero", "fatima.romero@example.com", "Granada"),
                "Calle San Juan 4, Granada",
                "Paid",
                "2026-04-21T08:30:00Z",
                List.of(
                        orderLine("products/1-A", "Cafe de especialidad 1kg", "Bebidas", 1, "21.90"),
                        orderLine("products/9-A", "Granola con frutos secos 500g", "Despensa", 2, "9.75"),
                        orderLine("products/8-A", "Galletas integrales 300g", "Snacks", 3, "3.85")
                ),
                List.of(
                        statusEntry("Pending", "2026-04-21T08:30:00Z", "Pedido creado"),
                        statusEntry("Paid", "2026-04-21T08:36:00Z", "Pago confirmado")
                )
        ), "orders/7-A");

        session.store(order(
                "customers/7-A",
                customerSnapshot("customers/7-A", "Gabriel Ruiz", "gabriel.ruiz@example.com", "Santiago de Compostela"),
                "Rua do Franco 9, Santiago de Compostela",
                "Processing",
                "2026-04-21T17:20:00Z",
                List.of(
                        orderLine("products/5-A", "Cafe instantaneo 200g", "Bebidas", 5, "5.40"),
                        orderLine("products/7-A", "Infusion de frutos rojos 20u", "Bebidas", 2, "4.90")
                ),
                List.of(
                        statusEntry("Pending", "2026-04-21T17:20:00Z", "Pedido creado"),
                        statusEntry("Paid", "2026-04-21T17:26:00Z", "Pago confirmado"),
                        statusEntry("Processing", "2026-04-21T18:00:00Z", "Preparando pedido para envio agrupado")
                )
        ), "orders/8-A");

        session.store(order(
                "customers/8-A",
                customerSnapshot("customers/8-A", "Helena Torres", "helena.torres@example.com", "Zaragoza"),
                "Calle Alfonso I 22, Zaragoza",
                "Shipped",
                "2026-04-22T10:05:00Z",
                List.of(
                        orderLine("products/2-A", "Te verde organico 500g", "Bebidas", 3, "12.40"),
                        orderLine("products/11-A", "Matcha ceremonial 100g", "Bebidas", 1, "18.90"),
                        orderLine("products/12-A", "Barritas de avena 6u", "Snacks", 2, "4.50")
                ),
                List.of(
                        statusEntry("Pending", "2026-04-22T10:05:00Z", "Pedido creado"),
                        statusEntry("Paid", "2026-04-22T10:11:00Z", "Pago confirmado"),
                        statusEntry("Processing", "2026-04-22T11:00:00Z", "Empaquetado completado"),
                        statusEntry("Shipped", "2026-04-22T15:30:00Z", "Salida del almacen")
                )
        ), "orders/9-A");

        session.store(order(
                "customers/2-A",
                customerSnapshot("customers/2-A", "Bruno Diaz", "bruno.diaz@example.com", "Valencia"),
                "Avenida Colon 12, Valencia",
                "Pending",
                "2026-04-23T19:40:00Z",
                List.of(
                        orderLine("products/6-A", "Cafe descafeinado 250g", "Bebidas", 2, "7.80"),
                        orderLine("products/4-A", "Muesli sin azucar 750g", "Despensa", 1, "8.50"),
                        orderLine("products/10-A", "Crema de cacahuete 350g", "Despensa", 1, "6.60")
                ),
                List.of(
                        statusEntry("Pending", "2026-04-23T19:40:00Z", "Pedido creado")
                )
        ), "orders/10-A");

        session.store(order(
                "customers/3-A",
                customerSnapshot("customers/3-A", "Carla Martin", "carla.martin@example.com", "Sevilla"),
                "Calle Feria 8, Sevilla",
                "Delivered",
                "2026-04-24T07:55:00Z",
                List.of(
                        orderLine("products/1-A", "Cafe de especialidad 1kg", "Bebidas", 1, "21.90"),
                        orderLine("products/5-A", "Cafe instantaneo 200g", "Bebidas", 2, "5.40"),
                        orderLine("products/3-A", "Chocolate negro 70% 200g", "Snacks", 5, "6.95")
                ),
                List.of(
                        statusEntry("Pending", "2026-04-24T07:55:00Z", "Pedido creado"),
                        statusEntry("Paid", "2026-04-24T08:00:00Z", "Pago confirmado"),
                        statusEntry("Processing", "2026-04-24T08:40:00Z", "Pedido consolidado"),
                        statusEntry("Shipped", "2026-04-24T14:15:00Z", "Pedido enviado"),
                        statusEntry("Delivered", "2026-04-25T09:05:00Z", "Entrega completada")
                )
        ), "orders/11-A");

        session.store(order(
                "customers/5-A",
                customerSnapshot("customers/5-A", "Elena Soto", "elena.soto@example.com", "Barcelona"),
                "Paseo de Gracia 77, Barcelona",
                "Paid",
                "2026-04-25T13:10:00Z",
                List.of(
                        orderLine("products/7-A", "Infusion de frutos rojos 20u", "Bebidas", 3, "4.90"),
                        orderLine("products/8-A", "Galletas integrales 300g", "Snacks", 2, "3.85"),
                        orderLine("products/9-A", "Granola con frutos secos 500g", "Despensa", 1, "9.75")
                ),
                List.of(
                        statusEntry("Pending", "2026-04-25T13:10:00Z", "Pedido creado"),
                        statusEntry("Paid", "2026-04-25T13:16:00Z", "Pago confirmado")
                )
        ), "orders/12-A");
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
