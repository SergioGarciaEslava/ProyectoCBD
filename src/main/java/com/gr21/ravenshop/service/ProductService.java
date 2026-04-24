package com.gr21.ravenshop.service;

import com.gr21.ravenshop.dto.PaginationResponse;
import com.gr21.ravenshop.dto.ProductCreateRequest;
import com.gr21.ravenshop.dto.ProductPageResponse;
import com.gr21.ravenshop.dto.ProductResponse;
import com.gr21.ravenshop.model.Product;
import com.gr21.ravenshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse create(ProductCreateRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setCategory(request.category());
        product.setTags(request.tags() == null ? Collections.emptyList() : request.tags());
        product.setUnitPrice(request.unitPrice());
        product.setActive(request.active() == null || request.active());

        Product saved = productRepository.save(product);
        return toResponse(saved);
    }

    public Optional<ProductResponse> getById(String productId) {
        return productRepository.findById(normalizeId(productId)).map(this::toResponse);
    }

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public ProductPageResponse list(int page, int size) {
        List<ProductResponse> all = productRepository.findAll().stream()
                .filter(Product::isActive)
                .map(this::toResponse)
                .toList();
        List<ProductResponse> paged = paginate(all, page, size);
        return new ProductPageResponse(paged, new PaginationResponse(page, size, all.size()));
    }

    public Optional<ProductResponse> update(String productId, ProductCreateRequest request) {
        return productRepository.findById(normalizeId(productId))
                .map(existing -> {
                    existing.setName(request.name());
                    existing.setDescription(request.description());
                    existing.setCategory(request.category());
                    existing.setTags(request.tags() == null ? Collections.emptyList() : request.tags());
                    existing.setUnitPrice(request.unitPrice());
                    if (request.active() != null) {
                        existing.setActive(request.active());
                    }
                    return toResponse(productRepository.save(existing));
                });
    }

    public boolean deactivate(String productId) {
        Optional<Product> existing = productRepository.findById(normalizeId(productId));
        if (existing.isEmpty()) {
            return false;
        }
        Product product = existing.get();
        product.setActive(false);
        productRepository.save(product);
        return true;
    }

    private String normalizeId(String productId) {
        return productId.contains("/") ? productId : "products/" + productId;
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getTags(),
                product.getUnitPrice(),
                product.isActive()
        );
    }

    private List<ProductResponse> paginate(List<ProductResponse> items, int page, int size) {
        int from = Math.min(page * size, items.size());
        int to = Math.min(from + size, items.size());
        return items.subList(from, to);
    }
}
