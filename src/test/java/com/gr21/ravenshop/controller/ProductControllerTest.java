package com.gr21.ravenshop.controller;

import com.gr21.ravenshop.model.Product;
import com.gr21.ravenshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    void listViewRendersProducts() throws Exception {
        Product product = new Product();
        product.setId("products/1-A");
        product.setName("Cafe");
        product.setCategory("Bebidas");
        product.setPrice(new BigDecimal("19.90"));
        product.setStock(50);
        given(productService.listProducts()).willReturn(List.of(product));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products/list"))
                .andExpect(model().attributeExists("products"))
                .andExpect(content().string(containsString("Cafe")))
                .andExpect(content().string(containsString("Bebidas")))
                .andExpect(content().string(containsString("19.90")))
                .andExpect(content().string(containsString("50")));
    }

    @Test
    void listViewShowsFriendlyMessageWhenThereAreNoProducts() throws Exception {
        given(productService.listProducts()).willReturn(List.of());

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products/list"))
                .andExpect(content().string(containsString("No hay productos disponibles todavia")));
    }

    @Test
    void newFormRendersFormView() throws Exception {
        mockMvc.perform(get("/products/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("products/form"))
                .andExpect(model().attributeExists("form"));
    }

    @Test
    void createFromFormRedirectsToProductsAndSplitsTags() throws Exception {
        mockMvc.perform(post("/products")
                        .param("name", "Cafe")
                        .param("category", "Bebidas")
                        .param("price", "19.90")
                        .param("stock", "50")
                        .param("tagsText", "premium, arabica, origen"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products"));

        verify(productService).createProduct(
                "Cafe",
                "Bebidas",
                new BigDecimal("19.90"),
                50,
                List.of("premium", "arabica", "origen")
        );
    }

    @Test
    void createFromFormShowsErrorsAndDoesNotSaveWhenValidationFails() throws Exception {
        mockMvc.perform(post("/products")
                        .param("name", "Cafe")
                        .param("category", "")
                        .param("price", "0")
                        .param("stock", "-1")
                        .param("tagsText", "premium"))
                .andExpect(status().isOk())
                .andExpect(view().name("products/form"))
                .andExpect(content().string(containsString("La categoria es obligatoria")))
                .andExpect(content().string(containsString("El precio debe ser mayor que 0")))
                .andExpect(content().string(containsString("El stock no puede ser negativo")))
                .andExpect(content().string(containsString("value=\"Cafe\"")))
                .andExpect(content().string(containsString("value=\"premium\"")));

        verify(productService, never()).createProduct(
                org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.any(),
                org.mockito.ArgumentMatchers.anyInt(),
                org.mockito.ArgumentMatchers.anyList()
        );
    }

    @Test
    void createFromFormRequiresName() throws Exception {
        mockMvc.perform(post("/products")
                        .param("name", "")
                        .param("category", "Bebidas")
                        .param("price", "19.90")
                        .param("stock", "5")
                        .param("tagsText", "premium"))
                .andExpect(status().isOk())
                .andExpect(view().name("products/form"))
                .andExpect(content().string(containsString("El nombre es obligatorio")))
                .andExpect(content().string(containsString("value=\"Bebidas\"")))
                .andExpect(content().string(containsString("value=\"premium\"")));

        verify(productService, never()).createProduct(
                org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.any(),
                org.mockito.ArgumentMatchers.anyInt(),
                org.mockito.ArgumentMatchers.anyList()
        );
    }
}
