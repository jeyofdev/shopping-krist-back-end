package com.jeyofdev.shopping_krist.domain.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeyofdev.shopping_krist.domain.city.City;
import com.jeyofdev.shopping_krist.domain.profile.Profile;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", columnDefinition = "VARCHAR(100)")
    @NotNull(message = AddressValidationMessages.REQUIRED_NAME)
    @Size(min = 5, max = 100, message = AddressValidationMessages.VALID_NAME)
    private String name;

    @Column(name = "phone", columnDefinition = "VARCHAR(10)")
    @NotNull(message = AddressValidationMessages.REQUIRED_PHONE)
    @Pattern(regexp = "^\\d{10}$", message = AddressValidationMessages.VALID_PHONE)
    private String phone;

    @Column(name = "street_number", columnDefinition = "VARCHAR(4)")
    @NotNull(message = AddressValidationMessages.REQUIRED_STREET_NUMBER)
    @Pattern(regexp = "^[0-9]{1,4}$", message = AddressValidationMessages.VALID_STREET_NUMBER)
    private String streetNumber;

    @Column(name = "street", columnDefinition = "VARCHAR(100)")
    @NotNull(message = AddressValidationMessages.REQUIRED_STREET)
    @Size(min = 3, max = 100, message = AddressValidationMessages.VALID_STREET_SIZE)
    private String street;

    @Column(name = "zip_code", columnDefinition = "VARCHAR(5)")
    @NotNull(message = AddressValidationMessages.REQUIRED_ZIP_CODE)
    @Pattern(regexp = "\\d{5}", message = AddressValidationMessages.VALID_ZIP_CODE)
    private String zipCode;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @JsonIgnore
    private Profile profile;
}
