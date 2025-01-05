package com.jeyofdev.shopping_krist.domain.product;

import com.jeyofdev.shopping_krist.core.interfaces.mapper.IDomainMapper;
import com.jeyofdev.shopping_krist.domain.address.dto.AddressDTO;
import com.jeyofdev.shopping_krist.domain.comment.dto.CommentDTO;
import com.jeyofdev.shopping_krist.domain.product.dto.ProductDTO;
import com.jeyofdev.shopping_krist.domain.product.dto.SaveProductDTO;
import com.jeyofdev.shopping_krist.format.CartItemPreviewFormat;
import com.jeyofdev.shopping_krist.format.ListRelationFormat;
import com.jeyofdev.shopping_krist.format.PriceFormat;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class ProductDomainMapper implements IDomainMapper<Product, ProductDTO, SaveProductDTO> {
    @Override
    public ProductDTO mapFromEntity(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getBrand(),
                product.getName(),
                product.getDescription(),
                PriceFormat.builder()
                        .price(product.getPrice())
                        .oldPrice(product.getOldPrice())
                        .build(),
                product.getStock(),
                product.getColor(),
                product.getSize(),
                ListRelationFormat.<CommentDTO>builder()
                        .size(product.getCommentList().size())
                        .results(product.getCommentList().isEmpty() ? Collections.emptyList() : product.getCommentList().stream()
                                .map(comment -> new CommentDTO(
                                        comment.getId(),
                                        comment.getTitle(),
                                        comment.getReview(),
                                        comment.getRating(),
                                        comment.getCreatedAt()
                                )).collect(Collectors.toList())
                        )
                        .build()

        );
    }

    @Override
    public Product mapToEntity(SaveProductDTO saveProductDTO) {
        return Product.builder()
                .brand(saveProductDTO.brand())
                .name(saveProductDTO.name())
                .description(saveProductDTO.description())
                .price(saveProductDTO.price())
                .oldPrice(saveProductDTO.oldPrice())
                .stock(saveProductDTO.stock())
                .color(saveProductDTO.color())
                .size(saveProductDTO.size())
                .build();
    }
}
