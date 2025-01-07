package com.jeyofdev.shopping_krist.domain.cartItem.dto;

import com.jeyofdev.shopping_krist.core.enums.ColorEnum;
import com.jeyofdev.shopping_krist.core.enums.SizeEnum;
import com.jeyofdev.shopping_krist.format.PriceFormat;

import java.util.UUID;

public record CartItemDTO(
        UUID id,
        Integer quantity,
        UUID productId,
        String brand,
        String name,
        PriceFormat price,
        ColorEnum colorEnum,
        SizeEnum sizeEnum,
        UUID cartId
) {
}
