package com.jeyofdev.shopping_krist.domain.notification.dto;

public record SaveNotificationDTO(
        String title,
        String description,
        boolean isRead
) {
}
