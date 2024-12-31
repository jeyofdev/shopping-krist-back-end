package com.jeyofdev.shopping_krist.domain.notification;

import com.jeyofdev.shopping_krist.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public Notification findById(@PathVariable("notificationId") UUID notificationId) throws NotFoundException {
        return notificationRepository.findById(notificationId).orElseThrow(
                () -> new NotFoundException(MessageFormat.format(" Entity Notification with id {0} cannot be found", notificationId)));
    }

    public Notification save(Notification city) {
        return notificationRepository.save(city);
    }

    public Notification updateById(UUID notificationId, Notification updatedNotification) {
        Notification existingNotification = findById(notificationId);
        Notification existingNotificationUpdated = Notification.builder()
                .id(notificationId)
                .title(updatedNotification.getTitle() != null ? updatedNotification.getTitle() : existingNotification.getTitle())
                .description(updatedNotification.getDescription() != null ? updatedNotification.getDescription() : existingNotification.getDescription())
                .build();

        return notificationRepository.save(existingNotificationUpdated);
    }

    @Transactional
    public void deleteById(UUID notificationId) {
        findById(notificationId);
        notificationRepository.deleteById(notificationId);
    }
}
