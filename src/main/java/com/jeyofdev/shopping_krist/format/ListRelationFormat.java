package com.jeyofdev.shopping_krist.format;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ListRelationFormat<T> {
    private int size;
    private List<T> results;
}
