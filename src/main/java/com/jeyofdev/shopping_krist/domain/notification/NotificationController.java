package com.jeyofdev.shopping_krist.domain.notification;

import com.jeyofdev.shopping_krist.core.constants.ApiRoutes;
import com.jeyofdev.shopping_krist.core.models.DomainSuccessResponse;
import com.jeyofdev.shopping_krist.domain.notification.dto.NotificationDTO;
import com.jeyofdev.shopping_krist.domain.notification.dto.SaveNotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiRoutes.BASE_API_V1 + ApiRoutes.NOTIFICATION)
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    private final NotificationMapper notificationMapper;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<DomainSuccessResponse<List<NotificationDTO>>> findAllNotification() {
        List<Notification> notificationList = notificationService.findAll();
        List<NotificationDTO> notificationDTOList = notificationList.stream().map(notificationMapper::mapFromEntity).toList();

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, notificationDTOList),
                HttpStatus.OK
        );
    }

    @GetMapping("/{notificationId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<DomainSuccessResponse<NotificationDTO>> findNotificationById(@PathVariable("notificationId") UUID notificationId) {
        Notification notification = notificationService.findById(notificationId);
        NotificationDTO notificationDTO = notificationMapper.mapFromEntity(notification);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, notificationDTO),
                HttpStatus.OK
        );
    }

    @PostMapping(ApiRoutes.PROFILE + "/{profileId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<DomainSuccessResponse<NotificationDTO>> saveNotification(
            @RequestBody SaveNotificationDTO saveNotificationDTO,
            @PathVariable("profileId") UUID profileId
    ) {
        Notification notification = notificationMapper.mapToEntity(saveNotificationDTO);
        Notification newNotification = notificationService.save(notification, profileId);
        NotificationDTO newNotificationDTO = notificationMapper.mapFromEntity(newNotification);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.CREATED, newNotificationDTO),
                HttpStatus.OK
        );
    }

    @PutMapping("/{notificationId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<DomainSuccessResponse<NotificationDTO>> updateNotificationById(
            @PathVariable("notificationId") UUID notificationId,
            @RequestBody SaveNotificationDTO saveNotificationDTO
    ) {
        Notification notification = notificationMapper.mapToEntity(saveNotificationDTO);
        Notification updateNotification = notificationService.updateById(notificationId, notification);
        NotificationDTO updateNotificationDTO = notificationMapper.mapFromEntity(updateNotification);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, updateNotificationDTO),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{notificationId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<DomainSuccessResponse<Object>> deleteNotificationById(@PathVariable("notificationId") UUID notificationId) {
        String message = notificationService.deleteById(notificationId);

        return new ResponseEntity<>(
                DomainSuccessResponse.get(HttpStatus.OK, message),
                HttpStatus.OK
        );
    }
}
