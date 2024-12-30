package com.jeyofdev.shopping_krist.domain.profile.dto;

import java.util.UUID;

public record ProfileDTO(
        UUID id,
        String firstname,
        String lastname,
        String phone,
        String address
) {
}
