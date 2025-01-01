package com.jeyofdev.shopping_krist.domain.order.dto;

import com.jeyofdev.shopping_krist.domain.profile.Profile;

import java.util.Date;
import java.util.UUID;

public record OrderDTO(
        UUID id,
        Date createdAt,
        String status,
        Profile profile
) {
}
