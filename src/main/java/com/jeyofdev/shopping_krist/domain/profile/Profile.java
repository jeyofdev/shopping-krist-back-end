package com.jeyofdev.shopping_krist.domain.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeyofdev.shopping_krist.auth_user.AuthUser;
import com.jeyofdev.shopping_krist.domain.address.Address;
import com.jeyofdev.shopping_krist.domain.cart.Cart;
import com.jeyofdev.shopping_krist.domain.notification.Notification;
import com.jeyofdev.shopping_krist.domain.order.Order;
import com.jeyofdev.shopping_krist.domain.product.Product;
import com.jeyofdev.shopping_krist.domain.profileSettings.ProfileSettings;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "The firstname field is required.")
    @Size(min = 2, max = 30, message = "The firstname field must contain between {min} and {max} characters.")
    private String firstname;

    @Column(name = "lastname", columnDefinition = "VARCHAR(80)")
    @NotNull(message = "The lastname field is required.")
    @Size(min = 3, max = 80, message = "The lastname field must contain between {min} and {max} characters.")
    private String lastname;

    @Column(name = "phone", columnDefinition = "VARCHAR(10)")
    @NotNull(message = "The phone number field is required.")
    @Pattern(regexp = "^\\+?[0-9]*$", message = "Please provide a valid phone number.")
    private String phone;

    @Column(name = "address", columnDefinition = "VARCHAR(150)")
    @NotNull(message = "The address field is required.")
    @Size(min = 10, max = 255, message = "The address field must contain between {min} and {max} characters.")
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
