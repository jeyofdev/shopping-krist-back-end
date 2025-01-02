package com.jeyofdev.shopping_krist.domain.notification;

import com.jeyofdev.shopping_krist.domain.notification.dto.NotificationDTO;
import com.jeyofdev.shopping_krist.domain.notification.dto.SaveNotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    private final NotificationDomainMapper notificationMapper;

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> findAllNotification() {
        List<Notification> notificationList = notificationService.findAll();
        List<NotificationDTO> notificationDTOList = notificationList.stream().map(notificationMapper::mapFromEntity).toList();

        return new ResponseEntity<>(notificationDTOList, HttpStatus.OK);
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationDTO> findNotificationById(@PathVariable("notificationId") UUID notificationId) {
        Notification notification = notificationService.findById(notificationId);
        NotificationDTO notificationDTO = notificationMapper.mapFromEntity(notification);

        return new ResponseEntity<>(notificationDTO, HttpStatus.OK);
    }

    @PostMapping("profile/{profileId}")
    public ResponseEntity<NotificationDTO> saveNotification(
            @RequestBody SaveNotificationDTO saveNotificationDTO,
            @PathVariable("profileId") UUID profileId
    ) {
        Notification notification = notificationMapper.mapToEntity(saveNotificationDTO);
        Notification newNotification = notificationService.save(notification, profileId);
        NotificationDTO newNotificationDTO = notificationMapper.mapFromEntity(newNotification);

        return new ResponseEntity<>(newNotificationDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{notificationId}")
    public ResponseEntity<NotificationDTO> updateNotificationById(
            @PathVariable("notificationId") UUID notificationId,
            @RequestBody SaveNotificationDTO saveNotificationDTO
    ) {
        Notification notification = notificationMapper.mapToEntity(saveNotificationDTO);
        Notification updateNotification = notificationService.updateById(notificationId, notification);
        NotificationDTO updateNotificationDTO = notificationMapper.mapFromEntity(updateNotification);

        return new ResponseEntity<>(updateNotificationDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<String> deleteNotificationById(@PathVariable("notificationId") UUID notificationId) {
        notificationService.deleteById(notificationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
