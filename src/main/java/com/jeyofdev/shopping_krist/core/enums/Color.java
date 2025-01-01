package com.jeyofdev.shopping_krist.core.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jeyofdev.shopping_krist.core.serializer.ColorDeserializer;

@JsonDeserialize(using = ColorDeserializer.class)
public enum Color {
    BLACK,
    WHITE,
    BLUE,
    BROWN,
    GREEN,
    GREY,
    ORANGE,
    PINK,
    PURPLE,
    RED,
    YELLOW
}
