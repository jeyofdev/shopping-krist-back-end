package com.jeyofdev.shopping_krist.domain.product.dto;

import com.jeyofdev.shopping_krist.core.enums.Color;
import com.jeyofdev.shopping_krist.core.enums.Size;

import java.util.List;
import java.util.UUID;

public record SaveProductDTO(
        String brand,
        String name,
        String description,
        Double price,
        Double oldPrice,
        int stock,
        Color color,
        Size size,
        List<UUID> categoryIds
) {

}
