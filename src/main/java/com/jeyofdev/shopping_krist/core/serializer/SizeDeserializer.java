package com.jeyofdev.shopping_krist.core.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.jeyofdev.shopping_krist.core.enums.SizeEnum;

import java.io.IOException;

public class SizeDeserializer extends JsonDeserializer<SizeEnum> {
    @Override
    public SizeEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText().toUpperCase();
        return SizeEnum.valueOf(value);
    }
}
