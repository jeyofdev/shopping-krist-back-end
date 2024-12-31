package com.jeyofdev.shopping_krist.domain.address;

import jakarta.persistence.*;
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
    private String name;

    @Column(name = "phone", columnDefinition = "VARCHAR(10)")
    private String phone;

    @Column(name = "street_number", columnDefinition = "VARCHAR(4)")
    private String streetNumber;

    @Column(name = "street", columnDefinition = "VARCHAR(50)")
    private String street;

    @Column(name = "zip_code", columnDefinition = "VARCHAR(5)")
    private String zipCode;

    // todo : relation address-city
}
