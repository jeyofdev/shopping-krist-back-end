package com.jeyofdev.shopping_krist.core.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.jeyofdev.shopping_krist.core.enums.DarkModeEnum;

import java.io.IOException;

public class DarkModeDeserializer extends JsonDeserializer<DarkModeEnum> {
    @Override
    public DarkModeEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText().toUpperCase();
        return DarkModeEnum.valueOf(value);
    }
}
