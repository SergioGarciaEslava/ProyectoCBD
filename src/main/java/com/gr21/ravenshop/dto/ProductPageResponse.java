package com.gr21.ravenshop.dto;

import java.util.List;

public record ProductPageResponse(List<ProductResponse> items, PaginationResponse pagination) {
}
