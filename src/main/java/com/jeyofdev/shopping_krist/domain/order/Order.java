package com.jeyofdev.shopping_krist.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeyofdev.shopping_krist.domain.address.Address;
import com.jeyofdev.shopping_krist.domain.cartItem.CartItem;
import com.jeyofdev.shopping_krist.domain.profile.Profile;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "\"order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private Date createdAt;

    @Column(name = "status", columnDefinition = "VARCHAR(50)")
    @NotBlank(message = "The status field is required.")
    @Size(min = 3, max = 50, message = "The status field must contain between {min} and {max} characters.")
    private String status;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @JsonIgnore
    private Profile profile;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @JsonIgnore
    private Address shippingAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;
}
