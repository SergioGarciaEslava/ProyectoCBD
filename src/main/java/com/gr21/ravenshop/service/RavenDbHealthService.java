package com.gr21.ravenshop.service;

import net.ravendb.client.documents.IDocumentStore;
import net.ravendb.client.documents.operations.GetStatisticsOperation;
import net.ravendb.client.documents.session.IDocumentSession;
import org.springframework.stereotype.Service;

@Service
public class RavenDbHealthService {

    private final IDocumentStore documentStore;

    public RavenDbHealthService(IDocumentStore documentStore) {
        this.documentStore = documentStore;
    }

    public RavenDbHealth checkConnection() {
        try (IDocumentSession ignored = documentStore.openSession(documentStore.getDatabase())) {
            documentStore.maintenance().send(new GetStatisticsOperation());
            return new RavenDbHealth(true, "Connected");
        } catch (Exception exception) {
            return new RavenDbHealth(false, exception.getClass().getSimpleName() + ": " + exception.getMessage());
        }
    }
}
