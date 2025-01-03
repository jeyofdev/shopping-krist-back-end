package com.jeyofdev.shopping_krist.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDataResponse {
    private String firstname;
    private String lastname;
    private String phone;
    private String address;
}
