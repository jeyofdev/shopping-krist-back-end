package com.jeyofdev.shopping_krist.domain.address.dto;

import com.jeyofdev.shopping_krist.format.AddressFormat;

import java.util.UUID;

public record AddressDTO(
        UUID id,
        String name,
        String phone,
        AddressFormat address
) {
}
