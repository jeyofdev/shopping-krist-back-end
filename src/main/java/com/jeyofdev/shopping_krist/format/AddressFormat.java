package com.jeyofdev.shopping_krist.format;

import com.jeyofdev.shopping_krist.domain.address.Address;
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

    @Builder
    public AddressFormat(String streetNumber, String street, String zipCode, String city) {
        this.streetNumber = streetNumber;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        setFullAddress();
    }

    public static AddressFormat get(Address address) {
        return AddressFormat.builder()
                .streetNumber(address.getStreetNumber())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .city(address.getCity() != null ? address.getCity().getName() : null)
                .build();
    }
}
