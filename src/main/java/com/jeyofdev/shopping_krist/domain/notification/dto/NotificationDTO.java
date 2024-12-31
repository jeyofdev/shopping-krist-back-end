package com.jeyofdev.shopping_krist.domain.notification.dto;

import java.util.UUID;

public record NotificationDTO(
        UUID id,
        String title,
        String description,
        boolean isRead
) {
}
