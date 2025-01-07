package com.jeyofdev.shopping_krist.core.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jeyofdev.shopping_krist.core.serializer.OrderStatusDeserializer;

@JsonDeserialize(using = OrderStatusDeserializer.class)
public enum OrderStatusEnum {
    PENDING,
    PROCESSED,
    SHIPPED,
    DELIVERED,
    CANCELLED,
    REFUNDED,
    RETURNED,
    COMPLETED;
}
