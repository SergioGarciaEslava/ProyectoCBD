package com.gr21.ravenshop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gr21.ravenshop.model.Product;
import net.ravendb.client.documents.IDocumentStore;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThatNoException;

class RavenDbConfigTest {

    @Test
    void documentStoreUsesMapperThatCanSerializeJavaTimeTypes() {
        RavenDbProperties properties = new RavenDbProperties();
        properties.setUrl("http://127.0.0.1:8085");
        properties.setDatabase("RavenShop");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        RavenDbConfig config = new RavenDbConfig();

        try (IDocumentStore store = config.documentStore(properties, objectMapper)) {
            Product product = new Product();
            product.setName("Cafe");
            product.setCategory("Bebidas");
            product.setPrice(new BigDecimal("19.90"));
            product.setStock(10);
            product.setCreatedAt(OffsetDateTime.parse("2026-04-24T12:00:00+02:00"));

            assertThatNoException()
                    .isThrownBy(() -> store.getConventions().getEntityMapper().valueToTree(product));
        }
    }
}
