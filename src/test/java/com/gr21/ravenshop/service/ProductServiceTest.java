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
import java.util.Optional;

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
}
