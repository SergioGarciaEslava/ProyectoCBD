package com.gr21.ravenshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gr21.ravenshop.dto.PaginationResponse;
import com.gr21.ravenshop.dto.ProductCreateRequest;
import com.gr21.ravenshop.dto.ProductPageResponse;
import com.gr21.ravenshop.dto.ProductResponse;
import com.gr21.ravenshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductApiController.class)
class ProductApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductService productService;

    @Test
    void createReturns201() throws Exception {
        ProductResponse response = new ProductResponse(
                "products/1-A",
                "Cafe",
                "Cafe de especialidad",
                "Bebidas",
                List.of("premium"),
                new BigDecimal("19.90"),
                true
        );
        given(productService.create(any(ProductCreateRequest.class))).willReturn(response);

        ProductCreateRequest request = new ProductCreateRequest(
                "Cafe",
                "Cafe de especialidad",
                "Bebidas",
                List.of("premium"),
                new BigDecimal("19.90"),
                true
        );

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("products/1-A")))
                .andExpect(jsonPath("$.name", is("Cafe")));
    }

    @Test
    void getByIdReturns404WhenNotFound() throws Exception {
        given(productService.getById("404-A")).willReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/products/404-A"))
                .andExpect(status().isNotFound());
    }

    @Test
    void listReturns200() throws Exception {
        ProductPageResponse response = new ProductPageResponse(
                List.of(new ProductResponse("products/1-A", "Cafe", null, null, List.of(), new BigDecimal("19.90"), true)),
                new PaginationResponse(0, 20, 1)
        );
        given(productService.list(0, 20)).willReturn(response);

        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pagination.totalElements", is(1)));
    }

    @Test
    void updateReturns200WhenProductExists() throws Exception {
        ProductResponse response = new ProductResponse(
                "products/1-A",
                "Cafe Editado",
                "Cafe de especialidad",
                "Bebidas",
                List.of("premium"),
                new BigDecimal("21.90"),
                true
        );
        willReturn(Optional.of(response)).given(productService).update(any(), any(ProductCreateRequest.class));

        ProductCreateRequest request = new ProductCreateRequest(
                "Cafe Editado",
                "Cafe de especialidad",
                "Bebidas",
                List.of("premium"),
                new BigDecimal("21.90"),
                true
        );

        mockMvc.perform(put("/api/v1/products/1-A")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Cafe Editado")));
    }

    @Test
    void deleteReturns204WhenProductExists() throws Exception {
        given(productService.deactivate("1-A")).willReturn(true);

        mockMvc.perform(delete("/api/v1/products/1-A"))
                .andExpect(status().isNoContent());
    }
}
