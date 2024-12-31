package com.jeyofdev.shopping_krist.domain.address.dto;

import java.util.UUID;

public record AddressDTO(
        UUID id,
        String name,
        String phone,
        String streetNumber,
        String street,
        String zipCode
) {
}
