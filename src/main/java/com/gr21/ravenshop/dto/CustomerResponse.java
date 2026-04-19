package com.gr21.ravenshop.dto;

public record CustomerResponse(
        String id,
        String fullName,
        String email,
        String phone,
        String city,
        String address
) {
}
