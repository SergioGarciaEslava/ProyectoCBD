package com.gr21.ravenshop.dto;

import java.util.List;

public record CustomerPageResponse(List<CustomerResponse> items, PaginationResponse pagination) {
}
