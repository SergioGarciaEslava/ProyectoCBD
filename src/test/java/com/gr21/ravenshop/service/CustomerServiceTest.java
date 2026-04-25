package com.gr21.ravenshop.service;

import com.gr21.ravenshop.model.Address;
import com.gr21.ravenshop.model.Customer;
import com.gr21.ravenshop.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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

    @Test
    void createCustomerBuildsDocumentAndGeneratesCreatedAt() {
        Address address = new Address("Calle Mayor 1", "Madrid", "28013");
        when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Customer saved = customerService.createCustomer(
                "Ana Lopez",
                "ana.lopez@example.com",
                "+34 600000000",
                address
        );

        assertThat(saved.getFullName()).isEqualTo("Ana Lopez");
        assertThat(saved.getEmail()).isEqualTo("ana.lopez@example.com");
        assertThat(saved.getPhone()).isEqualTo("+34 600000000");
        assertThat(saved.getAddress().getStreet()).isEqualTo("Calle Mayor 1");
        assertThat(saved.getAddress().getCity()).isEqualTo("Madrid");
        assertThat(saved.getAddress().getPostalCode()).isEqualTo("28013");
        assertThat(saved.getCreatedAt()).isNotNull();
    }

    @Test
    void findCustomerByIdNormalizesId() {
        Customer customer = new Customer();
        customer.setId("customers/1-A");
        when(customerRepository.findById("customers/1-A")).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerService.findCustomerById("1-A");

        assertThat(result).contains(customer);
    }

    @Test
    void updateCustomerKeepsOriginalCreatedAt() {
        OffsetDateTime createdAt = OffsetDateTime.parse("2026-04-24T10:15:30Z");
        Customer existing = new Customer();
        existing.setId("customers/1-A");
        existing.setFullName("Ana Lopez");
        existing.setEmail("ana.lopez@example.com");
        existing.setCreatedAt(createdAt);
        when(customerRepository.findById("customers/1-A")).thenReturn(Optional.of(existing));
        when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<Customer> result = customerService.updateCustomer(
                "1-A",
                "Ana Lopez Editada",
                "ana.editada@example.com",
                "+34 611111111",
                new Address("Calle Nueva 2", "Valencia", "46001")
        );

        assertThat(result).isPresent();
        Customer saved = result.get();
        assertThat(saved.getId()).isEqualTo("customers/1-A");
        assertThat(saved.getFullName()).isEqualTo("Ana Lopez Editada");
        assertThat(saved.getEmail()).isEqualTo("ana.editada@example.com");
        assertThat(saved.getPhone()).isEqualTo("+34 611111111");
        assertThat(saved.getAddress().getStreet()).isEqualTo("Calle Nueva 2");
        assertThat(saved.getAddress().getCity()).isEqualTo("Valencia");
        assertThat(saved.getAddress().getPostalCode()).isEqualTo("46001");
        assertThat(saved.getCreatedAt()).isEqualTo(createdAt);
        verify(customerRepository).save(existing);
    }
}
