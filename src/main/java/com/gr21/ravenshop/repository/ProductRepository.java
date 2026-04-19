package com.gr21.ravenshop.repository;

import com.gr21.ravenshop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> findById(String id);

    List<Product> findAll();
}
