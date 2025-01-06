package com.jeyofdev.shopping_krist.domain.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeyofdev.shopping_krist.domain.city.City;
import com.jeyofdev.shopping_krist.domain.profile.Profile;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "The name field is required.")
    @Size(min = 5, max = 100, message = "The name field must contain between {min} and {max} characters.")
    private String name;

    @Column(name = "phone", columnDefinition = "VARCHAR(10)")
    @NotNull(message = "The phone number field is required.")
    @Pattern(regexp = "^\\d{10}$", message = "Please provide a valid phone number.")
    private String phone;

    @Column(name = "street_number", columnDefinition = "VARCHAR(4)")
    @NotBlank(message = "The name street number is required.")
    @Pattern(regexp = "^[0-9]{1,4}$", message = "The street number must be a maximum of 4 digits.")
    private String streetNumber;

    @Column(name = "street", columnDefinition = "VARCHAR(100)")
    @NotBlank(message = "The street field is required.")
    @Size(min = 3, max = 100, message = "The street field must contain between {min} and {max} characters.")
    private String street;

    @Column(name = "zip_code", columnDefinition = "VARCHAR(5)")
    @NotBlank(message = "The zip code field is required.")
    @Pattern(regexp = "\\d{5}", message = "The zip code must be exactly 5 digits.")
    private String zipCode;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @JsonIgnore
    private Profile profile;
}
