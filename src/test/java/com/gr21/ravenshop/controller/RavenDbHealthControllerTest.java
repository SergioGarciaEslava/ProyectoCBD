package com.gr21.ravenshop.controller;

import com.gr21.ravenshop.config.RavenDbProperties;
import com.gr21.ravenshop.service.RavenDbHealth;
import com.gr21.ravenshop.service.RavenDbHealthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RavenDbHealthController.class)
class RavenDbHealthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RavenDbHealthService ravenDbHealthService;

    @MockitoBean
    private RavenDbProperties ravenDbProperties;

    @Test
    void returnsUpWhenRavenDbConnectionIsAvailable() throws Exception {
        given(ravenDbHealthService.checkConnection()).willReturn(new RavenDbHealth(true, "Connected"));
        given(ravenDbProperties.getUrl()).willReturn("http://127.0.0.1:8085");
        given(ravenDbProperties.getDatabase()).willReturn("RavenShop");

        mockMvc.perform(get("/health-db"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status", is("UP")))
            .andExpect(jsonPath("$.url", is("http://127.0.0.1:8085")))
            .andExpect(jsonPath("$.database", is("RavenShop")))
            .andExpect(jsonPath("$.details", is("Connected")));
    }
}
