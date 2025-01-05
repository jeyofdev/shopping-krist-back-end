package com.jeyofdev.shopping_krist.domain.product;

import com.jeyofdev.shopping_krist.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @NonNull
    Optional<Product> findById(@NonNull UUID id);

    void deleteById(@NonNull UUID id);

    @Query("SELECT p FROM Product p JOIN p.categoryList c WHERE c = :category")
    List<Product> findByCategory(@Param("category") Category category);
}