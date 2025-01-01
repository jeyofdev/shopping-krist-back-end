package com.jeyofdev.shopping_krist.core.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jeyofdev.shopping_krist.core.serializer.SizeDeserializer;

@JsonDeserialize(using = SizeDeserializer.class)
public enum Size {
    S,
    M,
    L,
    XL,
    XXL
}
