package com.gr21.ravenshop.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponse(
        String id,
        String name,
        String description,
        String category,
        List<String> tags,
        BigDecimal unitPrice,
        boolean active
) {
}
