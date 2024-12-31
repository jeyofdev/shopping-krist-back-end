package com.jeyofdev.shopping_krist.domain.profile.dto;

import com.jeyofdev.shopping_krist.domain.address.Address;

import java.util.List;
import java.util.UUID;

public record ProfileDTO(
        UUID id,
        String firstname,
        String lastname,
        String phone,
        String email,
        String address,
        List<Address> deliveryAddressList
) {
}
