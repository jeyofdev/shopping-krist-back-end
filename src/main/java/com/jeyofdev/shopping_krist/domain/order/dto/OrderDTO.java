package com.jeyofdev.shopping_krist.domain.order.dto;

import java.util.Date;
import java.util.UUID;

public record OrderDTO(
        UUID id,
        Date createdAt,
        String status
) {
}
