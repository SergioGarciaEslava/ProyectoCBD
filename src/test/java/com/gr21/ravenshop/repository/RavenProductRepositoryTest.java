package com.gr21.ravenshop.repository;

import com.gr21.ravenshop.model.Product;
import net.ravendb.client.documents.IDocumentStore;
import net.ravendb.client.documents.session.IAdvancedSessionOperations;
import net.ravendb.client.documents.session.IDocumentSession;
import net.ravendb.client.documents.session.IRawDocumentQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RavenProductRepositoryTest {

    @Mock
    private IDocumentStore documentStore;

    @Mock
    private IDocumentSession session;

    @Mock
    private IAdvancedSessionOperations advancedSessionOperations;

    @Mock
    private IRawDocumentQuery<Product> rawQuery;

    private RavenProductRepository repository;

    @BeforeEach
    void setUp() {
        repository = new RavenProductRepository(documentStore);
        when(documentStore.getDatabase()).thenReturn("RavenShop");
        when(documentStore.openSession("RavenShop")).thenReturn(session);
        when(session.advanced()).thenReturn(advancedSessionOperations);
    }

    @Test
    void findAllListsProductsByIdPrefixToIncludeSeededAndCreatedDocuments() {
        Product seeded = new Product();
        seeded.setId("products/1-A");
        Product created = new Product();
        created.setId("products/9-A");

        when(advancedSessionOperations.rawQuery(Product.class, "from @all_docs where startsWith(id(), $idPrefix)"))
                .thenReturn(rawQuery);
        when(rawQuery.addParameter("idPrefix", "products/")).thenReturn(rawQuery);
        when(rawQuery.toList()).thenReturn(List.of(seeded, created));

        List<Product> products = repository.findAll();

        assertThat(products).containsExactly(seeded, created);
        verify(advancedSessionOperations).rawQuery(Product.class, "from @all_docs where startsWith(id(), $idPrefix)");
        verify(rawQuery).addParameter("idPrefix", "products/");
    }
}
