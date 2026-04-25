package com.gr21.ravenshop.service;

import com.gr21.ravenshop.model.Customer;
import com.gr21.ravenshop.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> listCustomers() {
        return customerRepository.findAll();
    }
}
