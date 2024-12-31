package com.jeyofdev.shopping_krist.domain.city.dto;

import java.util.UUID;

public record CityDTO(
        UUID id,
        String name,
        String zipCode
) {
}
