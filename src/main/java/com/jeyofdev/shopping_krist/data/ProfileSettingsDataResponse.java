package com.jeyofdev.shopping_krist.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileSettingsDataResponse {
    private String appearance;

    @JsonProperty("isPushNotification")
    private boolean isPushNotification;

    @JsonProperty("isEmailNotification")
    private boolean isEmailNotification;
}
