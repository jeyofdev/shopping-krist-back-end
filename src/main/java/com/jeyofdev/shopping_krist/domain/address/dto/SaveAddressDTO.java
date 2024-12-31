package com.jeyofdev.shopping_krist.domain.address.dto;

public record SaveAddressDTO(
        String name,
        String phone,
        String streetNumber,
        String street,
        String zipCode
) {
}
