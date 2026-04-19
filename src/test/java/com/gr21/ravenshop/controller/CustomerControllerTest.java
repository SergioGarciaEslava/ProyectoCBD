package com.gr21.ravenshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gr21.ravenshop.dto.CustomerCreateRequest;
import com.gr21.ravenshop.dto.CustomerPageResponse;
import com.gr21.ravenshop.dto.CustomerResponse;
import com.gr21.ravenshop.dto.PaginationResponse;
import com.gr21.ravenshop.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CustomerService customerService;

    @Test
    void createReturns201() throws Exception {
        CustomerResponse response = new CustomerResponse(
                "customers/1-A",
                "Ana Lopez",
                "ana.lopez@example.com",
                "+34 600000000",
                "Madrid",
                "Calle Gran Via 1"
        );
        given(customerService.create(any(CustomerCreateRequest.class))).willReturn(response);

        CustomerCreateRequest request = new CustomerCreateRequest(
                "Ana Lopez",
                "ana.lopez@example.com",
                "+34 600000000",
                "Madrid",
                "Calle Gran Via 1"
        );

        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("customers/1-A")));
    }

    @Test
    void getByIdReturns404WhenNotFound() throws Exception {
        given(customerService.getById("404-A")).willReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/customers/404-A"))
                .andExpect(status().isNotFound());
    }

    @Test
    void listReturns200() throws Exception {
        CustomerPageResponse response = new CustomerPageResponse(
                List.of(new CustomerResponse("customers/1-A", "Ana Lopez", "ana.lopez@example.com", null, "Madrid", null)),
                new PaginationResponse(0, 20, 1)
        );
        given(customerService.list(0, 20)).willReturn(response);

        mockMvc.perform(get("/api/v1/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pagination.totalElements", is(1)));
    }
}
