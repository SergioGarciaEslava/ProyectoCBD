package com.gr21.ravenshop.seed;

import net.ravendb.client.documents.IDocumentStore;
import net.ravendb.client.documents.session.IDocumentSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.ApplicationArguments;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RavenDbSeedRunnerTest {

    private static final String SEED_MARKER_ID = "seed-data/ravenshop-wi003";

    @Mock
    private IDocumentStore documentStore;

    @Mock
    private IDocumentSession session;

    @Mock
    private ApplicationArguments args;

    private RavenDbSeedRunner runner;

    @BeforeEach
    void setUp() {
        runner = new RavenDbSeedRunner(documentStore);
        when(documentStore.getDatabase()).thenReturn("RavenShop");
        when(documentStore.openSession("RavenShop")).thenReturn(session);
    }

    @Test
    void shouldSeedDataWhenMarkerDoesNotExist() throws Exception {
        when(session.load(RavenDbSeedRunner.SeedMarker.class, SEED_MARKER_ID)).thenReturn(null);

        runner.run(args);

        verify(session, times(12)).store(any(), anyString());
        verify(session, times(1)).saveChanges();
    }

    @Test
    void shouldSkipSeedWhenMarkerAlreadyExists() throws Exception {
        when(session.load(RavenDbSeedRunner.SeedMarker.class, SEED_MARKER_ID))
                .thenReturn(new RavenDbSeedRunner.SeedMarker(SEED_MARKER_ID, "2026-04-19"));

        runner.run(args);

        verify(session, never()).store(any(), anyString());
        verify(session, never()).saveChanges();
    }
}
