package com.jeyofdev.shopping_krist.domain.product;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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
}
