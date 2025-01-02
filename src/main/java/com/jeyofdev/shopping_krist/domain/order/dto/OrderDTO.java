package com.jeyofdev.shopping_krist.domain.order.dto;

import com.jeyofdev.shopping_krist.domain.address.Address;
import com.jeyofdev.shopping_krist.domain.cartItem.dto.CartItemDTO;
import com.jeyofdev.shopping_krist.domain.profile.Profile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record OrderDTO(
        UUID id,
        Date createdAt,
        String status,
        Profile profile,
        Address shippingAddress,
        List<CartItemDTO> cartItems
) {
}
