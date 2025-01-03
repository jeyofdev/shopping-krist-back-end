package com.jeyofdev.shopping_krist.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDataResponse {
    private String name;
    private String phone;
    private String streetNumber;
    private String street;
    private String zipCode;
}