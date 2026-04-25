package com.gr21.ravenshop.service;

import com.gr21.ravenshop.dto.CustomerCreateRequest;
import com.gr21.ravenshop.dto.CustomerPageResponse;
import com.gr21.ravenshop.dto.CustomerResponse;
import com.gr21.ravenshop.dto.PaginationResponse;
import com.gr21.ravenshop.model.Address;
import com.gr21.ravenshop.model.Customer;
import com.gr21.ravenshop.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse create(CustomerCreateRequest request) {
        Customer customer = new Customer();
        customer.setFullName(request.fullName());
        customer.setEmail(request.email());
        customer.setPhone(request.phone());
        customer.setAddress(new Address(request.address(), request.city(), null));

        Customer saved = customerRepository.save(customer);
        return toResponse(saved);
    }

    public Optional<CustomerResponse> getById(String customerId) {
        return customerRepository.findById(normalizeId(customerId)).map(this::toResponse);
    }

    public CustomerPageResponse list(int page, int size) {
        List<CustomerResponse> all = customerRepository.findAll().stream().map(this::toResponse).toList();
        List<CustomerResponse> paged = paginate(all, page, size);
        return new CustomerPageResponse(paged, new PaginationResponse(page, size, all.size()));
    }

    private String normalizeId(String customerId) {
        return customerId.contains("/") ? customerId : "customers/" + customerId;
    }

    private CustomerResponse toResponse(Customer customer) {
        Address address = customer.getAddress();
        return new CustomerResponse(
                customer.getId(),
                customer.getFullName(),
                customer.getEmail(),
                customer.getPhone(),
                address.getCity(),
                address.getStreet()
        );
    }

    private List<CustomerResponse> paginate(List<CustomerResponse> items, int page, int size) {
        int from = Math.min(page * size, items.size());
        int to = Math.min(from + size, items.size());
        return items.subList(from, to);
    }
}
