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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
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

    @Test
    void newFormShowsCustomerForm() throws Exception {
        mockMvc.perform(get("/customers/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customers/form"))
                .andExpect(model().attributeExists("form"));
    }

    @Test
    void createFromFormRedirectsToCustomers() throws Exception {
        mockMvc.perform(post("/customers")
                        .param("fullName", "Ana Lopez")
                        .param("email", "ana.lopez@example.com")
                        .param("phone", "+34 600000000")
                        .param("address.street", "Calle Mayor 1")
                        .param("address.city", "Madrid")
                        .param("address.postalCode", "28013"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/customers"));

        verify(customerService).createCustomer(
                org.mockito.ArgumentMatchers.eq("Ana Lopez"),
                org.mockito.ArgumentMatchers.eq("ana.lopez@example.com"),
                org.mockito.ArgumentMatchers.eq("+34 600000000"),
                org.mockito.ArgumentMatchers.argThat(address ->
                        "Calle Mayor 1".equals(address.getStreet())
                                && "Madrid".equals(address.getCity())
                                && "28013".equals(address.getPostalCode()))
        );
    }

    @Test
    void createFromFormShowsErrorsAndDoesNotSaveWhenValidationFails() throws Exception {
        mockMvc.perform(post("/customers")
                        .param("fullName", "")
                        .param("email", "email-no-valido")
                        .param("phone", "+34 600000000")
                        .param("address.street", "Calle Mayor 1")
                        .param("address.city", "Madrid")
                        .param("address.postalCode", "28013"))
                .andExpect(status().isOk())
                .andExpect(view().name("customers/form"))
                .andExpect(content().string(containsString("El nombre completo es obligatorio")))
                .andExpect(content().string(containsString("El email no tiene un formato valido")))
                .andExpect(content().string(containsString("value=\"email-no-valido\"")))
                .andExpect(content().string(containsString("value=\"+34 600000000\"")))
                .andExpect(content().string(containsString("value=\"Calle Mayor 1\"")))
                .andExpect(content().string(containsString("value=\"Madrid\"")))
                .andExpect(content().string(containsString("value=\"28013\"")));

        verify(customerService, never()).createCustomer(
                org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.any()
        );
    }

    @Test
    void createFromFormValidatesRequiredAddressFields() throws Exception {
        mockMvc.perform(post("/customers")
                        .param("fullName", "Ana Lopez")
                        .param("email", "ana.lopez@example.com")
                        .param("phone", "+34 600000000")
                        .param("address.street", "")
                        .param("address.city", "")
                        .param("address.postalCode", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("customers/form"))
                .andExpect(content().string(containsString("La calle es obligatoria")))
                .andExpect(content().string(containsString("La ciudad es obligatoria")))
                .andExpect(content().string(containsString("El codigo postal es obligatorio")))
                .andExpect(content().string(containsString("value=\"Ana Lopez\"")))
                .andExpect(content().string(containsString("value=\"ana.lopez@example.com\"")))
                .andExpect(content().string(containsString("value=\"+34 600000000\"")));

        verify(customerService, never()).createCustomer(
                org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.any()
        );
    }
}
