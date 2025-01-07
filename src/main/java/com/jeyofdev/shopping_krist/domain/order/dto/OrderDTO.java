package com.jeyofdev.shopping_krist.domain.order.dto;

import com.jeyofdev.shopping_krist.core.enums.OrderStatusEnum;
import com.jeyofdev.shopping_krist.domain.address.dto.AddressDTO;
import com.jeyofdev.shopping_krist.domain.cartItem.dto.CartItemPreviewDTO;
import com.jeyofdev.shopping_krist.domain.profile.dto.ProfilePreviewDTO;
import com.jeyofdev.shopping_krist.format.ListRelationFormat;

import java.util.Date;
import java.util.UUID;

public record OrderDTO(
        UUID id,
        Date createdAt,
        OrderStatusEnum status,
        ProfilePreviewDTO profile,
        AddressDTO shippingAddress,
        ListRelationFormat<CartItemPreviewDTO> cartItems
) {
}
