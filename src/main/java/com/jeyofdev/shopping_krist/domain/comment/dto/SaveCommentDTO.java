package com.jeyofdev.shopping_krist.domain.comment.dto;

public record SaveCommentDTO(
        String title,
        String review,
        int rating
) {
}
