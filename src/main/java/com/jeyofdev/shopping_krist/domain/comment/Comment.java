package com.jeyofdev.shopping_krist.domain.comment;

import jakarta.persistence.*;
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
    private String title;

    @Column(name = "review", columnDefinition = "TEXT")
    private String review;

    @Column(name = "rating", columnDefinition = "INT")
    private Integer rating;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private Date createdAt;

}
