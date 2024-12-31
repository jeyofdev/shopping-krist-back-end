package com.jeyofdev.shopping_krist.domain.product;

import com.jeyofdev.shopping_krist.domain.product.dto.ProductDTO;
import com.jeyofdev.shopping_krist.domain.product.dto.SaveProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDTO mapFromEntity(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getBrand(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getOldPrice(),
                product.getStock()
        );
    }

    public Product mapToEntity(SaveProductDTO saveProductDTO) {
        return Product.builder()
                .brand(saveProductDTO.brand())
                .name(saveProductDTO.name())
                .description(saveProductDTO.description())
                .price(saveProductDTO.price())
                .oldPrice(saveProductDTO.oldPrice())
                .stock(saveProductDTO.stock())
                .build();
    }
}
