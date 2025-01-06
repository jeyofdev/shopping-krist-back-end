package com.jeyofdev.shopping_krist.domain.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeyofdev.shopping_krist.domain.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "title", columnDefinition = "VARCHAR(255)")
    @NotBlank(message = "The title field is required.")
    @Size(min = 3, max = 255, message = "The title field must contain between {min} and {max} characters.")
    private String title;

    @Column(name = "review", columnDefinition = "TEXT")
    @NotBlank(message = "The review field is required.")
    private String review;

    @Column(name = "rating", columnDefinition = "INT")
    @NotNull(message = "The rating field is required.")
    @Min(value = 1, message ="The minimum rating must be at least 1.")
    @Max(value = 5, message ="The maximum rating mut be 5.")
    private Integer rating;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private Date createdAt;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnore
    private Product product;
}
