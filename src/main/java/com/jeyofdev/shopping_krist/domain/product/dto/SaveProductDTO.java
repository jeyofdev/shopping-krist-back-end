package com.jeyofdev.shopping_krist.domain.product.dto;

import java.math.BigDecimal;

public record SaveProductDTO(
        String brand,
        String name,
        String description,
        BigDecimal price,
        BigDecimal oldPrice,
        int stock
) {
}
