package com.jeyofdev.shopping_krist.core.models;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class DomainSuccessResponse<T> {
    @Nullable private String message;
    private HttpStatus status;
    private T result;

    public static <T> DomainSuccessResponse<T> get(HttpStatus status, T result, String message) {
        return DomainSuccessResponse.<T>builder()
                .status(status)
                .result(result)
                .message(message)
                .build();
    }

    public static <T> DomainSuccessResponse<T> get(HttpStatus status, T result) {
        return DomainSuccessResponse.<T>builder()
                .status(status)
                .result(result)
                .message(null)
                .build();
    }

    public static <T> DomainSuccessResponse<T> get(HttpStatus status, String message) {
        return DomainSuccessResponse.<T>builder()
                .status(status)
                .result(null)
                .message(message)
                .build();
    }
}
