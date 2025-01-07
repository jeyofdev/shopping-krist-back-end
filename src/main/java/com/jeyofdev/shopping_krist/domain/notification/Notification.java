package com.jeyofdev.shopping_krist.domain.notification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeyofdev.shopping_krist.domain.profile.Profile;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "title", columnDefinition = "VARCHAR(200)")
    @NotNull(message = NotificationValidationMessages.REQUIRED_TITLE)
    @Size(min = 3, max = 200, message = NotificationValidationMessages.VALID_TITLE)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    @NotNull(message = NotificationValidationMessages.REQUIRED_DESCRIPTION)
    private String description;

    @Column(name = "is_read", columnDefinition = "BOOLEAN" , nullable = false)
    private boolean isRead;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @JsonIgnore
    private Profile profile;
}
