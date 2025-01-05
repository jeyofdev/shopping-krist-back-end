package com.jeyofdev.shopping_krist.domain.product.dto;

import com.jeyofdev.shopping_krist.core.enums.Color;
import com.jeyofdev.shopping_krist.core.enums.Size;
import com.jeyofdev.shopping_krist.domain.comment.dto.CommentDTO;
import com.jeyofdev.shopping_krist.format.ListRelationFormat;
import com.jeyofdev.shopping_krist.format.PriceFormat;

import java.util.UUID;

public record ProductDTO(
        UUID id,
        String brand,
        String name,
        String description,
        PriceFormat price,
        int stock,
        Color color,
        Size size,
        ListRelationFormat<CommentDTO> comments
) {
}
