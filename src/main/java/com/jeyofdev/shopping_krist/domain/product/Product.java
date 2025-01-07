package com.jeyofdev.shopping_krist.domain.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeyofdev.shopping_krist.annotation.ValidEnum;
import com.jeyofdev.shopping_krist.core.enums.ColorEnum;
import com.jeyofdev.shopping_krist.core.enums.SizeEnum;
import com.jeyofdev.shopping_krist.domain.cartItem.CartItem;
import com.jeyofdev.shopping_krist.domain.category.Category;
import com.jeyofdev.shopping_krist.domain.comment.Comment;
import com.jeyofdev.shopping_krist.domain.profile.Profile;
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
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "brand", columnDefinition = "VARCHAR(50)")
    @NotNull(message = ProductValidationMessages.REQUIRED_BRAND)
    @Size(min = 2, max = 30, message = ProductValidationMessages.VALID_BRAND)
    private String brand;

    @Column(name = "name", columnDefinition = "TEXT")
    @NotNull(message = ProductValidationMessages.REQUIRED_NAME)
    @Size(min = 2, message = ProductValidationMessages.VALID_NAME)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    @NotNull(message = ProductValidationMessages.REQUIRED_DESCRIPTION)
    private String description;

    @Column(name = "price", columnDefinition = "DECIMAL(10, 2)")
    @NotNull(message = ProductValidationMessages.REQUIRED_PRICE)
    @Min(value = 0, message = ProductValidationMessages.MIN_PRICE)
    private Double price;

    @Column(name = "old_price", columnDefinition = "DECIMAL(10, 2)")
    @NotNull(message = ProductValidationMessages.REQUIRED_OLD_PRICE)
    @Min(value = 0, message = ProductValidationMessages.MIN_OLD_PRICE)
    private Double oldPrice;

    @Column(name = "stock", columnDefinition = "INT")
    @NotNull(message = ProductValidationMessages.REQUIRED_STOCK)
    @Min(value = 0, message = ProductValidationMessages.MIN_STOCK)
    private Integer stock;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", columnDefinition = "VARCHAR(10)")
    @NotNull(message = ProductValidationMessages.REQUIRED_COLOR)
    @ValidEnum(enumClass = ColorEnum.class, message = ProductValidationMessages.VALID_COLOR)
    private ColorEnum color;

    @Enumerated(EnumType.STRING)
    @Column(name = "size", columnDefinition = "VARCHAR(3)")
    @NotNull(message = ProductValidationMessages.REQUIRED_SIZE)
    @ValidEnum(enumClass = SizeEnum.class, message = ProductValidationMessages.VALID_SIZE)
    private SizeEnum size;

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
