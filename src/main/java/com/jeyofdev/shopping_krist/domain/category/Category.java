package com.jeyofdev.shopping_krist.domain.category;

import com.jeyofdev.shopping_krist.domain.product.Product;
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
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    @NotNull(message = CategoryValidationMessages.REQUIRED_NAME)
    @Size(min = 3, max = 50, message = CategoryValidationMessages.VALID_NAME)
    private String name;

    @ManyToMany(mappedBy = "categoryList", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Product> productList = new ArrayList<>();
}

