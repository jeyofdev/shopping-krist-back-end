package com.jeyofdev.shopping_krist.domain.order.dto;

import com.jeyofdev.shopping_krist.core.enums.OrderStatusEnum;

import java.util.List;
import java.util.UUID;

public record SaveOrderDTO(
        OrderStatusEnum status,
        List<UUID> cartItemIds
) {
}
