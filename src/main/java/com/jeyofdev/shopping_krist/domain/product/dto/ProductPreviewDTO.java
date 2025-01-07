package com.jeyofdev.shopping_krist.domain.product.dto;

import com.jeyofdev.shopping_krist.core.enums.ColorEnum;
import com.jeyofdev.shopping_krist.core.enums.SizeEnum;
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
        ColorEnum color,
        SizeEnum size,
        int comments,
        ListRelationFormat<CategoryDTO> categories
) {
}
