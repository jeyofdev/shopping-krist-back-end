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
public class NotificationDataResponse {
    private String title;
    private String description;

    @JsonProperty("isRead")
    private boolean isRead;
}
