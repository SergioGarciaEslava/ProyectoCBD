package com.gr21.ravenshop.service;

import com.gr21.ravenshop.dto.CustomerCreateRequest;
import com.gr21.ravenshop.dto.CustomerResponse;
import com.gr21.ravenshop.model.Customer;
import com.gr21.ravenshop.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void createUsesRepositoryAndReturnsResponse() {
        CustomerCreateRequest request = new CustomerCreateRequest(
                "Ana Lopez",
                "ana.lopez@example.com",
                "+34 600000000",
                "Madrid",
                "Calle Gran Via 1"
        );

        when(customerRepository.save(org.mockito.ArgumentMatchers.any(Customer.class))).thenAnswer(invocation -> {
            Customer customer = invocation.getArgument(0);
            customer.setId("customers/1-A");
            return customer;
        });

        CustomerResponse response = customerService.create(request);

        assertThat(response.id()).isEqualTo("customers/1-A");
        assertThat(response.fullName()).isEqualTo("Ana Lopez");
        assertThat(response.email()).isEqualTo("ana.lopez@example.com");
    }

    @Test
    void getByIdReturnsEmptyWhenCustomerDoesNotExist() {
        when(customerRepository.findById("customers/404-A")).thenReturn(Optional.empty());

        Optional<CustomerResponse> result = customerService.getById("customers/404-A");

        assertThat(result).isEmpty();
    }
}
