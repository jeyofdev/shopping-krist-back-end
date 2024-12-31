package com.jeyofdev.shopping_krist.domain.cart.dto;

import java.util.Date;
import java.util.UUID;

public record CartDTO(
        UUID id,
        Date createdDate,
        Date updatedDate
) {
}
