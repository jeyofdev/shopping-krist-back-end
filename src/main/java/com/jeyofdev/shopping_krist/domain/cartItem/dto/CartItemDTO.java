package com.jeyofdev.shopping_krist.domain.cartItem.dto;

import com.jeyofdev.shopping_krist.domain.product.Product;

import java.util.UUID;

public record CartItemDTO(
        UUID id,
        Integer quantity,
        Product product
) {
}
