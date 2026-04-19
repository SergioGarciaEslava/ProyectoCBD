package com.gr21.ravenshop.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record ProductCreateRequest(
        @NotBlank String name,
        String description,
        String category,
        List<String> tags,
        @NotNull @DecimalMin("0.0") BigDecimal unitPrice,
        Boolean active
) {
}
