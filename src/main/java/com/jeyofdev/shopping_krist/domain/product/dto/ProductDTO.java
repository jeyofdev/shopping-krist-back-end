package com.jeyofdev.shopping_krist.domain.product.dto;

import com.jeyofdev.shopping_krist.core.enums.Color;
import com.jeyofdev.shopping_krist.core.enums.Size;

import java.util.UUID;

public record ProductDTO(
        UUID id,
        String brand,
        String name,
        String description,
        Double price,
        Double oldPrice,
        int stock,
        Color color,
        Size size
) {
}
