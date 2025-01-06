package com.jeyofdev.shopping_krist.domain.city;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    @NotBlank(message = "The name field is required.")
    @Size(min = 2, max = 50, message = "The name field must contain between {min} and {max} characters.")
    private String name;


    @Column(name = "zip_code", columnDefinition = "VARCHAR(5)")
    @NotBlank(message = "The zip code field is required.")
    @Pattern(regexp = "\\d{5}", message = "The zip code must be exactly 5 digits.")
    private String zipCode;
}
