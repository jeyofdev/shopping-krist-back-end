package com.jeyofdev.shopping_krist.domain.product.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDTO(
        UUID id,
        String brand,
        String name,
        String description,
        BigDecimal price,
        BigDecimal oldPrice,
        int stock
) {
}
