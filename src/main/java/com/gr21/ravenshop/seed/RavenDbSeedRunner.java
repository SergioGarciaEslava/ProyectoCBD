package com.gr21.ravenshop.seed;

import com.gr21.ravenshop.model.Address;
import com.gr21.ravenshop.model.Customer;
import com.gr21.ravenshop.model.Order;
import com.gr21.ravenshop.model.Product;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
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

    static final String SEED_MARKER_ID = "seed-data/ravenshop-wi023";

    private static final int GENERATED_PRODUCT_COUNT = 60;
    private static final int GENERATED_CUSTOMER_COUNT = 180;
    private static final int GENERATED_ORDER_COUNT = 800;

    private static final List<String> GENERATED_CITIES = List.of(
            "Madrid",
            "Valencia",
            "Sevilla",
            "Malaga",
            "Barcelona",
            "Granada",
            "Bilbao",
            "Zaragoza",
            "Murcia",
            "Alicante",
            "Valladolid",
            "Palma"
    );

    private static final List<String> FIRST_NAMES = List.of(
            "Adrian", "Beatriz", "Cesar", "Diana", "Estela", "Fernando", "Gloria", "Hector", "Ines",
            "Javier", "Lidia", "Marcos", "Nuria", "Oscar", "Paula"
    );

    private static final List<String> LAST_NAMES = List.of(
            "Alonso", "Blanco", "Crespo", "Dominguez", "Escudero", "Fuentes",
            "Gil", "Herrera", "Iglesias", "Jimenez", "Lozano", "Molina"
    );

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
        session.store(product("Aceite de oliva virgen extra 500ml", "Despensa", "10.90", 45,
                List.of("aceite", "mediterraneo", "cocina"), "2026-03-06T10:35:00Z"), "products/13-A");
        session.store(product("Miel de azahar 450g", "Despensa", "7.25", 60,
                List.of("miel", "natural", "desayuno"), "2026-03-08T12:15:00Z"), "products/14-A");
        session.store(product("Limonada artesanal 750ml", "Bebidas", "3.95", 95,
                List.of("limonada", "refresco", "artesanal"), "2026-03-10T09:45:00Z"), "products/15-A");
        session.store(product("Chips de garbanzo 120g", "Snacks", "2.90", 130,
                List.of("snack", "legumbre", "crujiente"), "2026-03-12T16:20:00Z"), "products/16-A");
        session.store(product("Pasta integral 500g", "Despensa", "2.75", 150,
                List.of("pasta", "integral", "cocina"), "2026-03-14T11:05:00Z"), "products/17-A");
        session.store(product("Kombucha jengibre 330ml", "Bebidas", "2.95", 85,
                List.of("kombucha", "jengibre", "fermentado"), "2026-03-16T14:30:00Z"), "products/18-A");

        storeGeneratedProducts(session);
    }

    private void storeGeneratedProducts(IDocumentSession session) {
        for (int index = 1; index <= GENERATED_PRODUCT_COUNT; index++) {
            session.store(product(
                    generatedProductName(index),
                    generatedProductCategory(index),
                    generatedProductPrice(index),
                    generatedProductStock(index),
                    generatedProductTags(index),
                    generatedProductCreatedAt(index)
            ), generatedProductId(index));
        }
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
        session.store(customer("Ivan Navarro", "ivan.navarro@example.com", "+34 600000009",
                "Calle Uria 15", "Oviedo", "33003", "2026-03-08T10:35:00Z"), "customers/9-A");
        session.store(customer("Julia Campos", "julia.campos@example.com", "+34 600000010",
                "Gran Via 42", "Bilbao", "48011", "2026-03-10T17:50:00Z"), "customers/10-A");
        session.store(customer("Karim Benali", "karim.benali@example.com", "+34 600000011",
                "Calle Traperia 6", "Murcia", "30001", "2026-03-12T09:15:00Z"), "customers/11-A");
        session.store(customer("Laura Vidal", "laura.vidal@example.com", "+34 600000012",
                "Rambla Nova 31", "Tarragona", "43003", "2026-03-15T13:25:00Z"), "customers/12-A");

        storeGeneratedCustomers(session);
    }

    private void storeGeneratedCustomers(IDocumentSession session) {
        for (int index = 1; index <= GENERATED_CUSTOMER_COUNT; index++) {
            String city = generatedCustomerCity(index);
            session.store(customer(
                    generatedCustomerFullName(index),
                    generatedCustomerEmail(index),
                    generatedCustomerPhone(index),
                    generatedCustomerStreet(index),
                    city,
                    generatedCustomerPostalCode(city, index),
                    generatedCustomerCreatedAt(index)
            ), generatedCustomerId(index));
        }
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

        session.store(order(
                "customers/9-A",
                customerSnapshot("customers/9-A", "Ivan Navarro", "ivan.navarro@example.com", "Oviedo"),
                "Calle Uria 15, Oviedo",
                "Pending",
                "2026-04-25T18:25:00Z",
                List.of(
                        orderLine("products/13-A", "Aceite de oliva virgen extra 500ml", "Despensa", 2, "10.90"),
                        orderLine("products/17-A", "Pasta integral 500g", "Despensa", 4, "2.75")
                ),
                List.of(
                        statusEntry("Pending", "2026-04-25T18:25:00Z", "Pedido creado")
                )
        ), "orders/13-A");

        session.store(order(
                "customers/10-A",
                customerSnapshot("customers/10-A", "Julia Campos", "julia.campos@example.com", "Bilbao"),
                "Gran Via 42, Bilbao",
                "Paid",
                "2026-04-26T09:05:00Z",
                List.of(
                        orderLine("products/14-A", "Miel de azahar 450g", "Despensa", 1, "7.25"),
                        orderLine("products/6-A", "Cafe descafeinado 250g", "Bebidas", 2, "7.80"),
                        orderLine("products/16-A", "Chips de garbanzo 120g", "Snacks", 3, "2.90")
                ),
                List.of(
                        statusEntry("Pending", "2026-04-26T09:05:00Z", "Pedido creado"),
                        statusEntry("Paid", "2026-04-26T09:09:00Z", "Pago confirmado")
                )
        ), "orders/14-A");

        session.store(order(
                "customers/11-A",
                customerSnapshot("customers/11-A", "Karim Benali", "karim.benali@example.com", "Murcia"),
                "Calle Traperia 6, Murcia",
                "Processing",
                "2026-04-26T11:35:00Z",
                List.of(
                        orderLine("products/15-A", "Limonada artesanal 750ml", "Bebidas", 6, "3.95"),
                        orderLine("products/18-A", "Kombucha jengibre 330ml", "Bebidas", 4, "2.95")
                ),
                List.of(
                        statusEntry("Pending", "2026-04-26T11:35:00Z", "Pedido creado"),
                        statusEntry("Paid", "2026-04-26T11:41:00Z", "Pago confirmado"),
                        statusEntry("Processing", "2026-04-26T12:10:00Z", "Preparando bebidas refrigeradas")
                )
        ), "orders/15-A");

        session.store(order(
                "customers/12-A",
                customerSnapshot("customers/12-A", "Laura Vidal", "laura.vidal@example.com", "Tarragona"),
                "Rambla Nova 31, Tarragona",
                "Shipped",
                "2026-04-26T15:20:00Z",
                List.of(
                        orderLine("products/11-A", "Matcha ceremonial 100g", "Bebidas", 1, "18.90"),
                        orderLine("products/14-A", "Miel de azahar 450g", "Despensa", 2, "7.25"),
                        orderLine("products/8-A", "Galletas integrales 300g", "Snacks", 2, "3.85")
                ),
                List.of(
                        statusEntry("Pending", "2026-04-26T15:20:00Z", "Pedido creado"),
                        statusEntry("Paid", "2026-04-26T15:28:00Z", "Pago confirmado"),
                        statusEntry("Processing", "2026-04-26T16:00:00Z", "Pedido empaquetado"),
                        statusEntry("Shipped", "2026-04-26T18:30:00Z", "Salida de almacen")
                )
        ), "orders/16-A");

        session.store(order(
                "customers/4-A",
                customerSnapshot("customers/4-A", "Diego Perez", "diego.perez@example.com", "Malaga"),
                "Calle Larios 18, Malaga",
                "Cancelled",
                "2026-04-27T08:45:00Z",
                List.of(
                        orderLine("products/18-A", "Kombucha jengibre 330ml", "Bebidas", 8, "2.95"),
                        orderLine("products/16-A", "Chips de garbanzo 120g", "Snacks", 5, "2.90")
                ),
                List.of(
                        statusEntry("Pending", "2026-04-27T08:45:00Z", "Pedido creado"),
                        statusEntry("Cancelled", "2026-04-27T09:30:00Z", "Cancelado por duplicado del cliente")
                )
        ), "orders/17-A");

        session.store(order(
                "customers/6-A",
                customerSnapshot("customers/6-A", "Fatima Romero", "fatima.romero@example.com", "Granada"),
                "Calle San Juan 4, Granada",
                "Delivered",
                "2026-04-27T12:15:00Z",
                List.of(
                        orderLine("products/13-A", "Aceite de oliva virgen extra 500ml", "Despensa", 1, "10.90"),
                        orderLine("products/17-A", "Pasta integral 500g", "Despensa", 6, "2.75"),
                        orderLine("products/15-A", "Limonada artesanal 750ml", "Bebidas", 4, "3.95")
                ),
                List.of(
                        statusEntry("Pending", "2026-04-27T12:15:00Z", "Pedido creado"),
                        statusEntry("Paid", "2026-04-27T12:20:00Z", "Pago confirmado"),
                        statusEntry("Processing", "2026-04-27T13:00:00Z", "Preparando lote familiar"),
                        statusEntry("Shipped", "2026-04-27T17:45:00Z", "Pedido enviado"),
                        statusEntry("Delivered", "2026-04-28T10:20:00Z", "Entrega completada")
                )
        ), "orders/18-A");

        session.store(order(
                "customers/8-A",
                customerSnapshot("customers/8-A", "Helena Torres", "helena.torres@example.com", "Zaragoza"),
                "Calle Alfonso I 22, Zaragoza",
                "Paid",
                "2026-04-28T10:40:00Z",
                List.of(
                        orderLine("products/5-A", "Cafe instantaneo 200g", "Bebidas", 3, "5.40"),
                        orderLine("products/12-A", "Barritas de avena 6u", "Snacks", 3, "4.50"),
                        orderLine("products/18-A", "Kombucha jengibre 330ml", "Bebidas", 2, "2.95")
                ),
                List.of(
                        statusEntry("Pending", "2026-04-28T10:40:00Z", "Pedido creado"),
                        statusEntry("Paid", "2026-04-28T10:48:00Z", "Pago confirmado")
                )
        ), "orders/19-A");

        session.store(order(
                "customers/10-A",
                customerSnapshot("customers/10-A", "Julia Campos", "julia.campos@example.com", "Bilbao"),
                "Gran Via 42, Bilbao",
                "Processing",
                "2026-04-28T16:05:00Z",
                List.of(
                        orderLine("products/1-A", "Cafe de especialidad 1kg", "Bebidas", 1, "21.90"),
                        orderLine("products/14-A", "Miel de azahar 450g", "Despensa", 1, "7.25"),
                        orderLine("products/4-A", "Muesli sin azucar 750g", "Despensa", 2, "8.50")
                ),
                List.of(
                        statusEntry("Pending", "2026-04-28T16:05:00Z", "Pedido creado"),
                        statusEntry("Paid", "2026-04-28T16:12:00Z", "Pago confirmado"),
                        statusEntry("Processing", "2026-04-28T16:55:00Z", "Preparando reposicion semanal")
                )
        ), "orders/20-A");

        storeGeneratedOrders(session);
    }

    private void storeGeneratedOrders(IDocumentSession session) {
        for (int index = 1; index <= GENERATED_ORDER_COUNT; index++) {
            int customerIndex = generatedCustomerIndexForOrder(index);
            String city = generatedCustomerCity(customerIndex);
            String orderedAt = generatedOrderCreatedAt(index);
            boolean madridHighValue = isMadridHighValueOrder(index);

            session.store(order(
                    generatedCustomerId(customerIndex),
                    customerSnapshot(
                            generatedCustomerId(customerIndex),
                            generatedCustomerFullName(customerIndex),
                            generatedCustomerEmail(customerIndex),
                            city
                    ),
                    generatedCustomerStreet(customerIndex) + ", " + city,
                    generatedOrderStatus(index, madridHighValue),
                    orderedAt,
                    generatedOrderLineItems(index, madridHighValue),
                    generatedStatusHistory(index, orderedAt, madridHighValue)
            ), generatedOrderId(index));
        }
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

    private String generatedProductId(int index) {
        return "products/" + (1000 + index) + "-A";
    }

    private String generatedProductName(int index) {
        int family = ((index - 1) / 4) + 1;
        return switch ((index - 1) % 4) {
            case 0 -> "Cafe origen microlote " + family;
            case 1 -> "Te funcional blend " + family;
            case 2 -> "Granola proteica lote " + family;
            default -> "Mix crujiente energia " + family;
        };
    }

    private String generatedProductCategory(int index) {
        return switch ((index - 1) % 4) {
            case 0, 1 -> "Bebidas";
            case 2 -> "Despensa";
            default -> "Snacks";
        };
    }

    private String generatedProductPrice(int index) {
        int family = (index - 1) / 4;
        return switch ((index - 1) % 4) {
            case 0 -> money(1890 + (family % 7) * 135);
            case 1 -> money(1040 + (family % 6) * 90);
            case 2 -> money(890 + (family % 5) * 85);
            default -> money(520 + (family % 6) * 55);
        };
    }

    private int generatedProductStock(int index) {
        return 30 + (index * 11 % 170);
    }

    private List<String> generatedProductTags(int index) {
        return switch ((index - 1) % 4) {
            case 0 -> List.of("cafe", "microlote", "demo");
            case 1 -> List.of("te", "blend", "bienestar");
            case 2 -> List.of("despensa", "proteina", "desayuno");
            default -> List.of("snack", "energia", "crujiente");
        };
    }

    private String generatedProductCreatedAt(int index) {
        return OffsetDateTime.parse("2026-03-20T08:00:00Z")
                .plusDays(index / 2L)
                .plusHours(index % 10L)
                .toString();
    }

    private String generatedCustomerId(int index) {
        return "customers/" + (1000 + index) + "-A";
    }

    private String generatedCustomerFullName(int index) {
        String firstName = FIRST_NAMES.get((index - 1) % FIRST_NAMES.size());
        String lastNameOne = LAST_NAMES.get((index - 1) % LAST_NAMES.size());
        String lastNameTwo = LAST_NAMES.get((index + 4) % LAST_NAMES.size());
        return firstName + " " + lastNameOne + " " + lastNameTwo;
    }

    private String generatedCustomerEmail(int index) {
        return "seed.customer." + String.format("%03d", index) + "@example.com";
    }

    private String generatedCustomerPhone(int index) {
        return "+34 61" + String.format("%07d", index);
    }

    private String generatedCustomerStreet(int index) {
        return "Avenida Demo " + (20 + index);
    }

    private String generatedCustomerCity(int index) {
        return GENERATED_CITIES.get((index - 1) % GENERATED_CITIES.size());
    }

    private String generatedCustomerPostalCode(String city, int index) {
        int suffix = 10 + (index % 80);
        return switch (city) {
            case "Madrid" -> "280" + String.format("%02d", suffix % 50);
            case "Valencia" -> "460" + String.format("%02d", suffix % 50);
            case "Sevilla" -> "410" + String.format("%02d", suffix % 50);
            case "Malaga" -> "290" + String.format("%02d", suffix % 50);
            case "Barcelona" -> "080" + String.format("%02d", suffix % 50);
            case "Granada" -> "180" + String.format("%02d", suffix % 50);
            case "Bilbao" -> "480" + String.format("%02d", suffix % 50);
            case "Zaragoza" -> "500" + String.format("%02d", suffix % 50);
            case "Murcia" -> "300" + String.format("%02d", suffix % 50);
            case "Alicante" -> "030" + String.format("%02d", suffix % 50);
            case "Valladolid" -> "470" + String.format("%02d", suffix % 50);
            default -> "070" + String.format("%02d", suffix % 50);
        };
    }

    private String generatedCustomerCreatedAt(int index) {
        return OffsetDateTime.parse("2026-03-18T09:00:00Z")
                .plusDays(index % 75L)
                .plusHours(index % 9L)
                .plusMinutes((index * 7L) % 60L)
                .toString();
    }

    private String generatedOrderId(int index) {
        return "orders/" + (1000 + index) + "-A";
    }

    private int generatedCustomerIndexForOrder(int index) {
        if (isMadridHighValueOrder(index)) {
            return 1 + (((index / 5) - 1) % 15) * GENERATED_CITIES.size();
        }
        return ((index * 7) % GENERATED_CUSTOMER_COUNT) + 1;
    }

    private boolean isMadridHighValueOrder(int index) {
        return index % 5 == 0;
    }

    private String generatedOrderCreatedAt(int index) {
        return OffsetDateTime.parse("2026-04-29T08:00:00Z")
                .plusHours(index * 3L)
                .plusMinutes((index * 11L) % 60L)
                .toString();
    }

    private String generatedOrderStatus(int index, boolean madridHighValue) {
        if (madridHighValue) {
            return switch (((index / 5) - 1) % 4) {
                case 0 -> "Paid";
                case 1 -> "Processing";
                case 2 -> "Shipped";
                default -> "Delivered";
            };
        }
        return switch ((index - 1) % 6) {
            case 0 -> "Pending";
            case 1 -> "Paid";
            case 2 -> "Processing";
            case 3 -> "Shipped";
            case 4 -> "Delivered";
            default -> "Cancelled";
        };
    }

    private List<Order.OrderLineItem> generatedOrderLineItems(int index, boolean madridHighValue) {
        List<Order.OrderLineItem> lineItems = new ArrayList<>();

        if (madridHighValue) {
            int coffeeIndex = 1 + (((index / 5) - 1) % 15) * 4;
            int teaIndex = coffeeIndex + 1;
            int granolaIndex = coffeeIndex + 2;

            lineItems.add(orderLine(
                    generatedProductId(coffeeIndex),
                    generatedProductName(coffeeIndex),
                    generatedProductCategory(coffeeIndex),
                    3 + (index % 3),
                    generatedProductPrice(coffeeIndex)
            ));
            lineItems.add(orderLine(
                    generatedProductId(teaIndex),
                    generatedProductName(teaIndex),
                    generatedProductCategory(teaIndex),
                    3 + (index % 2),
                    generatedProductPrice(teaIndex)
            ));
            lineItems.add(orderLine(
                    generatedProductId(granolaIndex),
                    generatedProductName(granolaIndex),
                    generatedProductCategory(granolaIndex),
                    4 + (index % 2),
                    generatedProductPrice(granolaIndex)
            ));
            return lineItems;
        }

        int firstIndex = ((index - 1) % GENERATED_PRODUCT_COUNT) + 1;
        int secondIndex = (firstIndex % GENERATED_PRODUCT_COUNT) + 1;
        int thirdIndex = ((firstIndex + 12) % GENERATED_PRODUCT_COUNT) + 1;

        lineItems.add(orderLine(
                generatedProductId(firstIndex),
                generatedProductName(firstIndex),
                generatedProductCategory(firstIndex),
                1 + (index % 3),
                generatedProductPrice(firstIndex)
        ));
        lineItems.add(orderLine(
                generatedProductId(secondIndex),
                generatedProductName(secondIndex),
                generatedProductCategory(secondIndex),
                1 + ((index + 1) % 3),
                generatedProductPrice(secondIndex)
        ));

        if (index % 2 == 0) {
            lineItems.add(orderLine(
                    generatedProductId(thirdIndex),
                    generatedProductName(thirdIndex),
                    generatedProductCategory(thirdIndex),
                    1 + ((index + 2) % 2),
                    generatedProductPrice(thirdIndex)
            ));
        }

        return lineItems;
    }

    private List<Order.StatusHistoryEntry> generatedStatusHistory(int index, String orderedAt, boolean madridHighValue) {
        List<Order.StatusHistoryEntry> history = new ArrayList<>();
        OffsetDateTime orderedAtTime = OffsetDateTime.parse(orderedAt);
        String currentStatus = generatedOrderStatus(index, madridHighValue);

        history.add(new Order.StatusHistoryEntry("Pending", orderedAtTime, "Pedido creado"));

        if ("Pending".equals(currentStatus)) {
            return history;
        }

        history.add(new Order.StatusHistoryEntry("Paid", orderedAtTime.plusMinutes(8), "Pago confirmado"));

        if ("Paid".equals(currentStatus)) {
            return history;
        }

        history.add(new Order.StatusHistoryEntry("Processing", orderedAtTime.plusHours(2), "Preparando pedido"));

        if ("Processing".equals(currentStatus)) {
            return history;
        }

        if ("Cancelled".equals(currentStatus)) {
            history.add(new Order.StatusHistoryEntry("Cancelled", orderedAtTime.plusHours(3), "Cancelado a solicitud del cliente"));
            return history;
        }

        history.add(new Order.StatusHistoryEntry("Shipped", orderedAtTime.plusHours(8), "Pedido enviado"));

        if ("Shipped".equals(currentStatus)) {
            return history;
        }

        history.add(new Order.StatusHistoryEntry("Delivered", orderedAtTime.plusDays(1), "Entrega completada"));
        return history;
    }

    private String money(int cents) {
        return BigDecimal.valueOf(cents, 2).toPlainString();
    }

    public record SeedMarker(String id, String seededAt) {
    }
}
