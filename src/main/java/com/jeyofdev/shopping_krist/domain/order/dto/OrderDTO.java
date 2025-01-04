package com.jeyofdev.shopping_krist.domain.order.dto;

import com.jeyofdev.shopping_krist.domain.address.dto.AddressDTO;
import com.jeyofdev.shopping_krist.domain.cartItem.dto.CartItemDTO;
import com.jeyofdev.shopping_krist.format.CartItemPreviewFormat;
import com.jeyofdev.shopping_krist.format.ListRelationFormat;
import com.jeyofdev.shopping_krist.format.ProfilePreviewFormat;

import java.util.Date;
import java.util.UUID;

public record OrderDTO(
        UUID id,
        Date createdAt,
        String status,
        ProfilePreviewFormat profile,
        AddressDTO shippingAddress,
        ListRelationFormat<CartItemPreviewFormat> cartItems
) {
}
