package com.jeyofdev.shopping_krist.format;

import lombok.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ListRelationFormat<T> {
    private int size;
    private List<T> results;

    public static <T, R> ListRelationFormat<R> get(List<T> items, Function<T, R> mapper) {
        return ListRelationFormat.<R>builder()
                .size(items.size())
                .results(items.stream().map(mapper).collect(Collectors.toList()))
                .build();
    }
}
