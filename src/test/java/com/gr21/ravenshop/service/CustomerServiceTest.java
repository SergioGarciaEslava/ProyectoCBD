package com.gr21.ravenshop.service;

import com.gr21.ravenshop.model.Customer;
import com.gr21.ravenshop.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void listCustomersReturnsRepositoryDocuments() {
        Customer customer = new Customer();
        customer.setId("customers/1-A");
        customer.setFullName("Ana Lopez");
        when(customerRepository.findAll()).thenReturn(List.of(customer));

        List<Customer> result = customerService.listCustomers();

        assertThat(result).containsExactly(customer);
    }
}
