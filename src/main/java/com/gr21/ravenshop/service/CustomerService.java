package com.gr21.ravenshop.service;

import com.gr21.ravenshop.model.Address;
import com.gr21.ravenshop.model.Customer;
import com.gr21.ravenshop.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
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

    public Customer createCustomer(String fullName, String email, String phone, Address address) {
        Customer customer = new Customer();
        customer.setFullName(fullName);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setCreatedAt(OffsetDateTime.now());
        return customerRepository.save(customer);
    }
}
