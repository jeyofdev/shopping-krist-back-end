package com.jeyofdev.shopping_krist.domain.product.dto;

import com.jeyofdev.shopping_krist.core.enums.ColorEnum;
import com.jeyofdev.shopping_krist.core.enums.SizeEnum;

import java.util.List;
import java.util.UUID;

public record SaveProductDTO(
        String brand,
        String name,
        String description,
        Double price,
        Double oldPrice,
        int stock,
        ColorEnum color,
        SizeEnum size,
        List<UUID> categoryIds
) {

}
