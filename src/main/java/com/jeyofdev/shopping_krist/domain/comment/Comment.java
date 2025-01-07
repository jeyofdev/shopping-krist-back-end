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
    @NotNull(message = CommentValidationMessages.REQUIRED_TITLE)
    @Size(min = 3, max = 255, message = CommentValidationMessages.VALID_TITLE)
    private String title;

    @Column(name = "review", columnDefinition = "TEXT")
    @NotNull(message = CommentValidationMessages.REQUIRED_REVIEW)
    private String review;

    @Column(name = "rating", columnDefinition = "INT")
    @NotNull(message = CommentValidationMessages.REQUIRED_RATING)
    @Min(value = 1, message = CommentValidationMessages.MIN_RATING)
    @Max(value = 5, message = CommentValidationMessages.MAX_RATING)
    private Integer rating;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private Date createdAt;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnore
    private Product product;
}
