package com.gr21.ravenshop.controller;

import com.gr21.ravenshop.config.RavenDbProperties;
import com.gr21.ravenshop.service.RavenDbHealthService;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RavenDbHealthController {

    private final RavenDbHealthService ravenDbHealthService;
    private final RavenDbProperties ravenDbProperties;

    public RavenDbHealthController(RavenDbHealthService ravenDbHealthService, RavenDbProperties ravenDbProperties) {
        this.ravenDbHealthService = ravenDbHealthService;
        this.ravenDbProperties = ravenDbProperties;
    }

    @GetMapping("/health/ravendb")
    public Map<String, String> health() {
        var health = ravenDbHealthService.checkConnection();
        return Map.of(
            "status", health.up() ? "UP" : "DOWN",
            "database", ravenDbProperties.getDatabase(),
            "details", health.details()
        );
    }
}
