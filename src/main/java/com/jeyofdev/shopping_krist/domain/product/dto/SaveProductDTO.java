package com.jeyofdev.shopping_krist.domain.product.dto;

import com.jeyofdev.shopping_krist.core.enums.Color;
import com.jeyofdev.shopping_krist.core.enums.Size;

import java.math.BigDecimal;

public record SaveProductDTO(
        String brand,
        String name,
        String description,
        BigDecimal price,
        BigDecimal oldPrice,
        int stock,
        Color color,
        Size size
) {
}
