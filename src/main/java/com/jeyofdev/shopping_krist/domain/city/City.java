package com.jeyofdev.shopping_krist.domain.city;

import com.jeyofdev.shopping_krist.core.constants.Regex;
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
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    @NotNull(message = CityValidationMessages.REQUIRED_NAME)
    @Size(min = 2, max = 50, message = CityValidationMessages.VALID_NAME)
    private String name;

    @Column(name = "zip_code", columnDefinition = "VARCHAR(5)")
    @NotNull(message = CityValidationMessages.REQUIRED_ZIP_CODE)
    @Pattern(regexp = Regex.ZIP_CODE_PATTERN, message = CityValidationMessages.VALID_ZIP_CODE)
    private String zipCode;
}
