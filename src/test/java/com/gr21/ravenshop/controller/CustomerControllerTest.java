package com.gr21.ravenshop.controller;

import com.gr21.ravenshop.model.Address;
import com.gr21.ravenshop.model.Customer;
import com.gr21.ravenshop.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerService customerService;

    @Test
    void listShowsCustomersPage() throws Exception {
        Customer customer = new Customer();
        customer.setId("customers/1-A");
        customer.setFullName("Ana Lopez");
        customer.setEmail("ana.lopez@example.com");
        customer.setPhone("+34 600000000");
        customer.setAddress(new Address(null, "Madrid", null));
        given(customerService.listCustomers()).willReturn(List.of(customer));

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(view().name("customers/list"))
                .andExpect(model().attributeExists("customers"))
                .andExpect(content().string(containsString("customers/1-A")))
                .andExpect(content().string(containsString("Ana Lopez")))
                .andExpect(content().string(containsString("ana.lopez@example.com")))
                .andExpect(content().string(containsString("Madrid")))
                .andExpect(content().string(containsString("+34 600000000")));
    }

    @Test
    void listShowsFriendlyMessageWhenThereAreNoCustomers() throws Exception {
        given(customerService.listCustomers()).willReturn(List.of());

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(view().name("customers/list"))
                .andExpect(content().string(containsString("No hay clientes registrados todavia")));
    }
}
