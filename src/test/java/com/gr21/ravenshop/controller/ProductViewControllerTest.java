package com.gr21.ravenshop.controller;

import com.gr21.ravenshop.dto.PaginationResponse;
import com.gr21.ravenshop.dto.ProductCreateRequest;
import com.gr21.ravenshop.dto.ProductPageResponse;
import com.gr21.ravenshop.dto.ProductResponse;
import com.gr21.ravenshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ProductViewController.class)
class ProductViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    void listViewRendersProducts() throws Exception {
        ProductPageResponse page = new ProductPageResponse(
                List.of(new ProductResponse("products/1-A", "Cafe", null, null, List.of(), new BigDecimal("19.90"), true)),
                new PaginationResponse(0, 20, 1)
        );
        given(productService.list(0, 50)).willReturn(page);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products/list"))
                .andExpect(model().attributeExists("items"))
                .andExpect(model().attribute("items", hasSize(1)));
    }

    @Test
    void createFromFormRedirectsToProducts() throws Exception {
        given(productService.create(any(ProductCreateRequest.class))).willReturn(
                new ProductResponse("products/1-A", "Cafe", null, null, List.of(), new BigDecimal("19.90"), true)
        );

        mockMvc.perform(post("/products")
                        .param("name", "Cafe")
                        .param("unitPrice", "19.90")
                        .param("active", "true"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));
    }
}
