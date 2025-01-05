package com.jeyofdev.shopping_krist.domain.cart.dto;

import com.jeyofdev.shopping_krist.domain.profile.dto.ProfilePreviewDTO;
import com.jeyofdev.shopping_krist.format.CartItemPreviewFormat;
import com.jeyofdev.shopping_krist.format.ListRelationFormat;

import java.util.Date;
import java.util.UUID;

public record CartDTO(
        UUID id,
        Date createdDate,
        Date updatedDate,
        ListRelationFormat<CartItemPreviewFormat> cartItemList,
        ProfilePreviewDTO profile
) {
}
