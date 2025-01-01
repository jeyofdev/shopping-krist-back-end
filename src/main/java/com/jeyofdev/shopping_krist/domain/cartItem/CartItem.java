package com.jeyofdev.shopping_krist.domain.cartItem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeyofdev.shopping_krist.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "quantity", columnDefinition = "INT")
    private Integer quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;
}
