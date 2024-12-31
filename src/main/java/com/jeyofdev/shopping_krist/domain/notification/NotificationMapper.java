package com.jeyofdev.shopping_krist.domain.notification;

import com.jeyofdev.shopping_krist.domain.notification.dto.NotificationDTO;
import com.jeyofdev.shopping_krist.domain.notification.dto.SaveNotificationDTO;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
    public NotificationDTO mapFromEntity(Notification notification) {
        return new NotificationDTO(
                notification.getId(),
                notification.getTitle(),
                notification.getDescription(),
                notification.isRead()
        );
    }

    public Notification mapToEntity(SaveNotificationDTO saveNotificationDTO) {
        return Notification.builder()
                .title(saveNotificationDTO.title())
                .description(saveNotificationDTO.description())
                .isRead(saveNotificationDTO.isRead())
                .build();
    }
}
