package com.jeyofdev.shopping_krist.domain.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeyofdev.shopping_krist.core.enums.Color;
import com.jeyofdev.shopping_krist.core.enums.DarkMode;
import com.jeyofdev.shopping_krist.core.enums.Size;
import com.jeyofdev.shopping_krist.domain.cartItem.CartItem;
import com.jeyofdev.shopping_krist.domain.category.Category;
import com.jeyofdev.shopping_krist.domain.comment.Comment;
import com.jeyofdev.shopping_krist.domain.notification.Notification;
import com.jeyofdev.shopping_krist.domain.profile.Profile;
import com.jeyofdev.shopping_krist.domain.profileSettings.ProfileSettings;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "brand", columnDefinition = "VARCHAR(50)")
    @NotNull(message = "The brand field is required.")
    @jakarta.validation.constraints.Size(min = 2, max = 30, message = "The brand field must contain between {min} and {max} characters.")
    private String brand;

    @Column(name = "name", columnDefinition = "TEXT")
    @NotNull(message = "The name field is required.")
    @jakarta.validation.constraints.Size(min = 2, message = "The name field must be at least {min} characters.")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    @NotBlank(message = "The review field is required.")
    private String description;

    @Column(name = "price", columnDefinition = "DECIMAL(10, 2)")
    @NotNull(message = "The price field is required.")
    @Min(value = 0, message = "The price must be at least 0.")
    private Double price;

    @Column(name = "old_price", columnDefinition = "DECIMAL(10, 2)")
    @NotNull(message = "The old price field is required.")
    @Min(value = 0, message = "The old price must be at least 0.")
    private Double oldPrice;

    @Column(name = "stock", columnDefinition = "INT")
    @NotNull(message = "The stock field is required.")
    @Min(value = 0, message ="The minimum stock must be at least 0.")
    private Integer stock;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", columnDefinition = "VARCHAR(10)")
    private Color color;

    @Enumerated(EnumType.STRING)
    @Column(name = "size", columnDefinition = "VARCHAR(3)")
    private Size size;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> commentList = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categoryList = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "product_profile",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private List<Profile> profileList = new ArrayList<>();
}
