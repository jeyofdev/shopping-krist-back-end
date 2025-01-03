package com.jeyofdev.shopping_krist.domain.cartItem.dto;

import com.jeyofdev.shopping_krist.core.enums.Color;
import com.jeyofdev.shopping_krist.core.enums.Size;
import com.jeyofdev.shopping_krist.domain.cart.Cart;
import com.jeyofdev.shopping_krist.format.PriceFormat;

import java.util.UUID;

public record CartItemDTO(
        UUID id,
        Integer quantity,
        UUID productId,
        String brand,
        String name,
        PriceFormat price,
        Color color,
        Size size,
        UUID cartId
) {
}
