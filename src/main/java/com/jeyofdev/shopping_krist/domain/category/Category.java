package com.jeyofdev.shopping_krist.domain.category;

import com.jeyofdev.shopping_krist.domain.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    @NotBlank(message = "The name field is required.")
    @Size(min = 3, max = 50, message = "The name field must contain between {min} and {max} characters.")
    private String name;

    @ManyToMany(mappedBy = "categoryList", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Product> productList = new ArrayList<>();
}

