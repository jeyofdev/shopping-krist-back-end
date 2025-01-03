package com.jeyofdev.shopping_krist.format;

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
}
