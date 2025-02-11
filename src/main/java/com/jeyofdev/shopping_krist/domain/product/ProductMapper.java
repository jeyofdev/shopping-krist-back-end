package com.jeyofdev.shopping_krist.domain.product;

import com.jeyofdev.shopping_krist.core.interfaces.mapper.IDomainMapper;
import com.jeyofdev.shopping_krist.domain.category.Category;
import com.jeyofdev.shopping_krist.domain.category.CategoryRepository;
import com.jeyofdev.shopping_krist.domain.category.dto.CategoryDTO;
import com.jeyofdev.shopping_krist.domain.comment.dto.CommentDTO;
import com.jeyofdev.shopping_krist.domain.product.dto.ProductDTO;
import com.jeyofdev.shopping_krist.domain.product.dto.SaveProductDTO;
import com.jeyofdev.shopping_krist.exception.NotFoundException;
import com.jeyofdev.shopping_krist.format.ListRelationFormat;
import com.jeyofdev.shopping_krist.format.PriceFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductMapper implements IDomainMapper<Product, ProductDTO, SaveProductDTO> {
    private final CategoryRepository categoryRepository;

    @Override
    public ProductDTO mapFromEntity(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getBrand(),
                product.getName(),
                product.getDescription(),
                PriceFormat.get(product),
                product.getStock(),
                product.getColor(),
                product.getSize(),
                getCommentListResponse(product),
                getCategoryListResponse(product)
        );
    }

    @Override
    public Product mapToEntity(SaveProductDTO saveProductDTO, Product existingProduct) {
        List<UUID> categoryIds = saveProductDTO.categoryIds() != null && !saveProductDTO.categoryIds().isEmpty()
                ? saveProductDTO.categoryIds()
                : existingProduct != null ? existingProduct.getCategoryList().stream().map(Category::getId).toList() : new ArrayList<>();

        List<Category> categoryList = categoryIds.stream()
                .map(categoryId -> categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new NotFoundException(MessageFormat.format("Category with id {0} not found", categoryId))))
                .toList();

        return Product.builder()
                .brand(saveProductDTO.brand())
                .name(saveProductDTO.name())
                .description(saveProductDTO.description())
                .price(saveProductDTO.price())
                .oldPrice(saveProductDTO.oldPrice())
                .stock(saveProductDTO.stock())
                .color(saveProductDTO.color())
                .size(saveProductDTO.size())
                .categoryList(categoryList)
                .build();
    }

    private ListRelationFormat<CommentDTO> getCommentListResponse(Product product) {
        return ListRelationFormat.get(product.getCommentList(), comment -> new CommentDTO(
                comment.getId(),
                comment.getTitle(),
                comment.getReview(),
                comment.getRating(),
                comment.getCreatedAt()
        ));
    }

    private ListRelationFormat<CategoryDTO> getCategoryListResponse(Product product) {
        return ListRelationFormat.get(product.getCategoryList(), category -> new CategoryDTO(
                category.getId(),
                category.getName()
        ));
    }
}
