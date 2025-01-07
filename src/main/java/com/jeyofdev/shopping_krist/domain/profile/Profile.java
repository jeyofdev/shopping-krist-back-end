package com.jeyofdev.shopping_krist.domain.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeyofdev.shopping_krist.auth_user.AuthUser;
import com.jeyofdev.shopping_krist.core.constants.Regex;
import com.jeyofdev.shopping_krist.domain.address.Address;
import com.jeyofdev.shopping_krist.domain.cart.Cart;
import com.jeyofdev.shopping_krist.domain.notification.Notification;
import com.jeyofdev.shopping_krist.domain.order.Order;
import com.jeyofdev.shopping_krist.domain.product.Product;
import com.jeyofdev.shopping_krist.domain.profileSettings.ProfileSettings;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
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

    @Column(name = "firstname", columnDefinition = "VARCHAR(30)")
    @NotNull(message = ProfileValidationMessages.REQUIRED_FIRSTNAME)
    @Size(min = 2, max = 30, message = ProfileValidationMessages.VALID_FIRSTNAME)
    private String firstname;

    @Column(name = "lastname", columnDefinition = "VARCHAR(80)")
    @NotNull(message = ProfileValidationMessages.REQUIRED_LASTNAME)
    @Size(min = 3, max = 80, message = ProfileValidationMessages.VALID_LASTNAME)
    private String lastname;

    @Column(name = "phone", columnDefinition = "VARCHAR(19)")
    @NotNull(message = ProfileValidationMessages.REQUIRED_PHONE)
    @Pattern(regexp = Regex.PHONE_PATTERN, message = ProfileValidationMessages.VALID_PHONE)
    private String phone;

    @Column(name = "address", columnDefinition = "VARCHAR(150)")
    @NotNull(message = ProfileValidationMessages.REQUIRED_ADDRESS)
    @Size(min = 10, max = 255, message = ProfileValidationMessages.VALID_ADDRESS)
    private String address;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AuthUser user;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Address> deliveryAddressList = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "profile_settings_id", referencedColumnName = "id")
    @JsonIgnore
    private ProfileSettings profileSettings;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Notification> notificationList = new ArrayList<>();

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    @JsonIgnore
    private Cart cart;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orderList = new ArrayList<>();

    @ManyToMany(mappedBy = "profileList", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Product> productList = new ArrayList<>();
}
