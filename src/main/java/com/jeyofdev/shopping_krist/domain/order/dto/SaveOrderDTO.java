package com.jeyofdev.shopping_krist.domain.order.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record SaveOrderDTO(
        /*Date createdAt,*/
        String status,
        List<UUID> cartItemIds
) {
}
