package com.gr21.ravenshop.service;

import com.gr21.ravenshop.model.Address;
import com.gr21.ravenshop.model.Customer;
import com.gr21.ravenshop.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> listCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findCustomerById(String customerId) {
        return customerRepository.findById(normalizeId(customerId));
    }

    public Customer createCustomer(String fullName, String email, String phone, Address address) {
        Customer customer = new Customer();
        customer.setFullName(fullName);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setCreatedAt(OffsetDateTime.now());
        return customerRepository.save(customer);
    }

    public Optional<Customer> updateCustomer(String customerId, String fullName, String email, String phone, Address address) {
        return customerRepository.findById(normalizeId(customerId))
                .map(existing -> {
                    existing.setFullName(fullName);
                    existing.setEmail(email);
                    existing.setPhone(phone);
                    existing.setAddress(address);
                    return customerRepository.save(existing);
                });
    }

    private String normalizeId(String customerId) {
        return customerId.contains("/") ? customerId : "customers/" + customerId;
    }
}
