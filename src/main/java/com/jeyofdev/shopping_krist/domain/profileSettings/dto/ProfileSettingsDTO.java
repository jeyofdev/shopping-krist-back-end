package com.jeyofdev.shopping_krist.domain.profileSettings.dto;

import com.jeyofdev.shopping_krist.core.enums.DarkMode;

import java.util.UUID;

public record ProfileSettingsDTO(
        UUID id,
        DarkMode appearance,
        boolean isPushNotification,
        boolean isEmailNotification
) {
}
