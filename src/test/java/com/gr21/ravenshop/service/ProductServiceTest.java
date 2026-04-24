package com.gr21.ravenshop.service;

import com.gr21.ravenshop.dto.ProductCreateRequest;
import com.gr21.ravenshop.dto.ProductResponse;
import com.gr21.ravenshop.model.Product;
import com.gr21.ravenshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void createUsesRepositoryAndDefaultsActiveToTrue() {
        ProductCreateRequest request = new ProductCreateRequest(
                "Cafe",
                "Cafe de especialidad",
                "Bebidas",
                java.util.List.of("premium"),
                new BigDecimal("19.90"),
                null
        );

        when(productRepository.save(org.mockito.ArgumentMatchers.any(Product.class))).thenAnswer(invocation -> {
            Product product = invocation.getArgument(0);
            product.setId("products/1-A");
            return product;
        });

        ProductResponse response = productService.create(request);

        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(captor.capture());

        assertThat(captor.getValue().isActive()).isTrue();
        assertThat(response.id()).isEqualTo("products/1-A");
        assertThat(response.name()).isEqualTo("Cafe");
    }

    @Test
    void getByIdReturnsEmptyWhenProductDoesNotExist() {
        when(productRepository.findById("products/404-A")).thenReturn(Optional.empty());

        Optional<ProductResponse> result = productService.getById("products/404-A");

        assertThat(result).isEmpty();
    }

    @Test
    void createProductStoresCreatedAtGeneratedOnServer() {
        when(productRepository.save(org.mockito.ArgumentMatchers.any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product saved = productService.createProduct(
                "Cafe",
                "Bebidas",
                new BigDecimal("19.90"),
                50,
                List.of("premium", "arabica")
        );

        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(captor.capture());

        assertThat(captor.getValue().getCreatedAt()).isNotNull();
        assertThat(captor.getValue().getCreatedAt()).isBeforeOrEqualTo(OffsetDateTime.now());
        assertThat(captor.getValue().getPrice()).isEqualByComparingTo("19.90");
        assertThat(captor.getValue().getStock()).isEqualTo(50);
        assertThat(captor.getValue().getTags()).containsExactly("premium", "arabica");
        assertThat(saved.getCreatedAt()).isNotNull();
    }

    @Test
    void updateReturnsUpdatedProductWhenExists() {
        Product existing = new Product();
        existing.setId("products/1-A");
        existing.setName("Cafe");
        existing.setActive(true);

        when(productRepository.findById("products/1-A")).thenReturn(Optional.of(existing));
        when(productRepository.save(org.mockito.ArgumentMatchers.any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProductCreateRequest request = new ProductCreateRequest(
                "Cafe Premium",
                "Nueva descripcion",
                "Bebidas",
                List.of("premium"),
                new BigDecimal("24.90"),
                true
        );

        Optional<ProductResponse> result = productService.update("products/1-A", request);

        assertThat(result).isPresent();
        assertThat(result.get().name()).isEqualTo("Cafe Premium");
        assertThat(result.get().unitPrice()).isEqualByComparingTo("24.90");
    }

    @Test
    void deactivateMarksProductAsInactive() {
        Product existing = new Product();
        existing.setId("products/1-A");
        existing.setActive(true);

        when(productRepository.findById("products/1-A")).thenReturn(Optional.of(existing));
        when(productRepository.save(org.mockito.ArgumentMatchers.any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        boolean result = productService.deactivate("products/1-A");

        assertThat(result).isTrue();
        assertThat(existing.isActive()).isFalse();
        verify(productRepository).save(existing);
    }
}
