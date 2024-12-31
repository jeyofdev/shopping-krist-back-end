package com.jeyofdev.shopping_krist.domain.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jeyofdev.shopping_krist.auth_user.AuthUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "firstname", columnDefinition = "VARCHAR(50)")
    private String firstname;

    @Column(name = "lastname", columnDefinition = "VARCHAR(50)")
    private String lastname;

    @Column(name = "phone", columnDefinition = "VARCHAR(10)")
    private String phone;

    @Column(name = "address", columnDefinition = "VARCHAR(150)")
    private String address;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AuthUser user;
}
