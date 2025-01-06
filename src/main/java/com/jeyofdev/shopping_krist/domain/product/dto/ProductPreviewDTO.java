package com.jeyofdev.shopping_krist.domain.product.dto;

import com.jeyofdev.shopping_krist.core.enums.Color;
import com.jeyofdev.shopping_krist.core.enums.Size;
import com.jeyofdev.shopping_krist.domain.category.dto.CategoryDTO;
import com.jeyofdev.shopping_krist.format.ListRelationFormat;
import com.jeyofdev.shopping_krist.format.PriceFormat;

import java.util.UUID;

public record ProductPreviewDTO(
        UUID id,
        String brand,
        String name,
        String description,
        PriceFormat price,
        int stock,
        Color color,
        Size size,
        int comments,
        ListRelationFormat<CategoryDTO> categories
) {
}
