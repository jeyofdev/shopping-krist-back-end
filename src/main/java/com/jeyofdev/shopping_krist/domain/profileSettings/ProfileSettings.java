package com.jeyofdev.shopping_krist.domain.profileSettings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeyofdev.shopping_krist.annotation.ValidEnum;
import com.jeyofdev.shopping_krist.core.enums.DarkModeEnum;
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
@Table(name = "profile_settings")
public class ProfileSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "appearance", columnDefinition = "VARCHAR(5)")
    @NotNull(message = ProfileSettingsValidationMessages.REQUIRED_APPEARANCE)
    @ValidEnum(enumClass = DarkModeEnum.class, message = ProfileSettingsValidationMessages.VALID_APPEARANCE)
    private DarkModeEnum appearance;

    @Column(name = "push_notification", columnDefinition = "BOOLEAN" , nullable = false)
    private boolean isPushNotification;

    @Column(name = "email_notification", columnDefinition = "BOOLEAN" , nullable = false)
    private boolean isEmailNotification;

    @OneToOne(mappedBy = "profileSettings", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST})
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @JsonIgnore
    private Profile profile;
}
