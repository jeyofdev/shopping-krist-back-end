package com.jeyofdev.shopping_krist.domain.product;

import com.jeyofdev.shopping_krist.core.interfaces.mapper.IDomainMapper;
import com.jeyofdev.shopping_krist.domain.product.dto.ProductDTO;
import com.jeyofdev.shopping_krist.domain.product.dto.SaveProductDTO;
import com.jeyofdev.shopping_krist.format.PriceFormat;
import org.springframework.stereotype.Component;

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
                product.getSize()
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
