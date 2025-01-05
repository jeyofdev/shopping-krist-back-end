package com.jeyofdev.shopping_krist.format;

import com.jeyofdev.shopping_krist.domain.profile.Profile;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfilePreviewFormat {
    private UUID id;
    private NameFormat name;
    private String phone;
    private String address;
    private String email;

    public static ProfilePreviewFormat get(Profile profile) {
        return ProfilePreviewFormat.builder()
                .id(profile.getId())
                .name(NameFormat.get(profile))
                .phone(profile.getPhone())
                .address(profile.getAddress())
                .email(profile.getUser().getEmail())
                .build();
    }
}
