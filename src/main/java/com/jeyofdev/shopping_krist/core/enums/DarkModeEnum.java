package com.jeyofdev.shopping_krist.core.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jeyofdev.shopping_krist.core.serializer.DarkModeDeserializer;

@JsonDeserialize(using = DarkModeDeserializer.class)
public enum DarkModeEnum {
    DARK,
    LIGHT
}
