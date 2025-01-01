package com.jeyofdev.shopping_krist.domain.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeyofdev.shopping_krist.core.enums.Color;
import com.jeyofdev.shopping_krist.core.enums.DarkMode;
import com.jeyofdev.shopping_krist.core.enums.Size;
import com.jeyofdev.shopping_krist.domain.cartItem.CartItem;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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
    private String brand;

    @Column(name = "name", columnDefinition = "TEXT")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", columnDefinition = "DECIMAL(10, 2)")
    private BigDecimal price;

    @Column(name = "old_price", columnDefinition = "DECIMAL(10, 2)")
    private BigDecimal oldPrice;

    @Column(name = "stock", columnDefinition = "INT")
    private Integer stock;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", columnDefinition = "VARCHAR(10)")
    private Color color;

    @Enumerated(EnumType.STRING)
    @Column(name = "size", columnDefinition = "VARCHAR(3)")
    private Size size;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CartItem> cartItems;
}
