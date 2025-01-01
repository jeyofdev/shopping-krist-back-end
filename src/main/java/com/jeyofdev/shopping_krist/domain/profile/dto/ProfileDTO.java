package com.jeyofdev.shopping_krist.domain.profile.dto;

import com.jeyofdev.shopping_krist.domain.address.Address;
import com.jeyofdev.shopping_krist.domain.order.Order;
import com.jeyofdev.shopping_krist.domain.profileSettings.ProfileSettings;

import java.util.List;
import java.util.UUID;

public record ProfileDTO(
        UUID id,
        String firstname,
        String lastname,
        String phone,
        String email,
        String address,
        List<Address> deliveryAddressList,
        ProfileSettings profileSettings,
        List<Order> orderList
) {
}
