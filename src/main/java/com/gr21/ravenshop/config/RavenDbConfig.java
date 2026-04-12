package com.gr21.ravenshop.config;

import net.ravendb.client.documents.DocumentStore;
import net.ravendb.client.documents.IDocumentStore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RavenDbProperties.class)
public class RavenDbConfig {

    @Bean(destroyMethod = "close")
    public IDocumentStore documentStore(RavenDbProperties properties) {
        var store = new DocumentStore();
        store.setUrls(properties.getUrls().toArray(String[]::new));
        store.setDatabase(properties.getDatabase());
        store.initialize();
        return store;
    }
}
