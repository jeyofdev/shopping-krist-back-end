package com.jeyofdev.shopping_krist.domain.profile.dto;

public record SaveProfileDTO(
        String firstname,
        String lastname,
        String phone,
        String address
) {
}
