package com.jeyofdev.shopping_krist.domain.cartItem.dto;

import com.jeyofdev.shopping_krist.format.CartItemPreviewFormat;
import com.jeyofdev.shopping_krist.format.ProductPreviewFormat;

import java.util.UUID;

public record CartItemPreviewDTO(
        UUID id,
        int quantity,
        ProductPreviewFormat product
) {
    public static CartItemPreviewDTO fromFormat(CartItemPreviewFormat format) {
        return new CartItemPreviewDTO(
                format.getId(),
                format.getQuantity(),
                format.getProduct()
        );
    }
}
