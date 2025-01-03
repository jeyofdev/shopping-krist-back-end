package com.jeyofdev.shopping_krist.domain.notification;

import com.jeyofdev.shopping_krist.core.abstracts.AbstractDomainServiceBase;
import com.jeyofdev.shopping_krist.domain.profile.Profile;
import com.jeyofdev.shopping_krist.domain.profile.ProfileRepository;
import com.jeyofdev.shopping_krist.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.UUID;

@Service
public class NotificationService extends AbstractDomainServiceBase<Notification, NotificationRepository> {
    private final NotificationRepository notificationRepository;
    private final ProfileRepository profileRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, ProfileRepository profileRepository) {
        super(notificationRepository, "notification");
        this.notificationRepository = notificationRepository;
        this.profileRepository = profileRepository;
    }

    public Notification save(Notification notification, UUID profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(
                () -> new NotFoundException(MessageFormat.format(" Profile Notification with id {0} cannot be found", profileId)));

        notification.setProfile(profile);
        return notificationRepository.save(notification);
    }

    public Notification updateById(UUID notificationId, Notification updatedNotification) {
        Notification existingNotification = findById(notificationId);
        Notification existingNotificationUpdated = Notification.builder()
                .id(notificationId)
                .title(updatedNotification.getTitle() != null ? updatedNotification.getTitle() : existingNotification.getTitle())
                .description(updatedNotification.getDescription() != null ? updatedNotification.getDescription() : existingNotification.getDescription())
                .isRead(updatedNotification.isRead() != existingNotification.isRead() ? updatedNotification.isRead() : existingNotification.isRead())
                .profile(existingNotification.getProfile())
                .build();

        return notificationRepository.save(existingNotificationUpdated);
    }

    @Transactional
    public void deleteById(UUID notificationId) {
        findById(notificationId);
        notificationRepository.deleteById(notificationId);
    }
}
