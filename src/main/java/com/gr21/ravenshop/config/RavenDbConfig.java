package com.gr21.ravenshop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.ravendb.client.documents.DocumentStore;
import net.ravendb.client.documents.IDocumentStore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RavenDbProperties.class)
public class RavenDbConfig {

    @Bean(destroyMethod = "close")
    public IDocumentStore documentStore(RavenDbProperties properties, ObjectMapper objectMapper) {
        var store = new DocumentStore();
        store.setUrls(new String[]{properties.getUrl()});
        store.setDatabase(properties.getDatabase());
        store.getConventions().setEntityMapper(objectMapper.copy());
        store.initialize();
        return store;
    }
}
