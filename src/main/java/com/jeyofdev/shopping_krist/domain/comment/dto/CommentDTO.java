package com.jeyofdev.shopping_krist.domain.comment.dto;

import java.util.Date;
import java.util.UUID;

public record CommentDTO(
        UUID id,
        String title,
        String review,
        Integer rating,
        Date createdDate
) {
}
