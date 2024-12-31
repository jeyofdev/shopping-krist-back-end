package com.jeyofdev.shopping_krist.domain.cartItem.dto;

import java.util.UUID;

public record CartItemDTO(
        UUID id,
        Integer quantity
) {
}
