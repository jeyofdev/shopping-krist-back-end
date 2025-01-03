package com.jeyofdev.shopping_krist.format;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressFormat {
    private String streetNumber;
    private String street;
    private String zipCode;
    private String city;
    private String fullAddress;

    public void setFullAddress() {
        this.fullAddress = String.format("%s %s, %s %s", this.streetNumber, this.street, this.zipCode, this.city);
    }

    public String getFullAddress() {
        if (this.fullAddress == null) {
            setFullAddress();
        }
        return this.fullAddress;

    }

    @Builder
    public AddressFormat(String streetNumber, String street, String zipCode, String city) {
        this.streetNumber = streetNumber;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        setFullAddress();
    }

}
