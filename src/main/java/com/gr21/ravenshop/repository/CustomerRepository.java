package com.gr21.ravenshop.repository;

import com.gr21.ravenshop.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Customer save(Customer customer);

    Optional<Customer> findById(String id);

    List<Customer> findAll();
}
