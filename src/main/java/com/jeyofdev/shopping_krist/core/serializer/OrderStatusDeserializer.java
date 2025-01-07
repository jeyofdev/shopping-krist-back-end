package com.jeyofdev.shopping_krist.core.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.jeyofdev.shopping_krist.core.enums.ColorEnum;
import com.jeyofdev.shopping_krist.core.enums.OrderStatusEnum;

import java.io.IOException;

public class OrderStatusDeserializer extends JsonDeserializer<OrderStatusEnum> {
    @Override
    public OrderStatusEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText().toUpperCase();
        return OrderStatusEnum.valueOf(value);
    }
}
