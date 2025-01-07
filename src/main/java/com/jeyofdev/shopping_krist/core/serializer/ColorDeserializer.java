package com.jeyofdev.shopping_krist.core.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.jeyofdev.shopping_krist.core.enums.ColorEnum;

import java.io.IOException;

public class ColorDeserializer extends JsonDeserializer<ColorEnum> {
    @Override
    public ColorEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText().toUpperCase();
        return ColorEnum.valueOf(value);
    }
}
