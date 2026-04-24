package com.gr21.ravenshop.repository;

import com.gr21.ravenshop.model.Order;

import java.util.Optional;

public interface OrderRepository {

    Optional<Order> findById(String id);
}
