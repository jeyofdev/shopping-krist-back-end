package com.jeyofdev.shopping_krist.domain.category;

import com.jeyofdev.shopping_krist.core.interfaces.mapper.IDomainMapper;
import com.jeyofdev.shopping_krist.domain.category.dto.CategoryDTO;
import com.jeyofdev.shopping_krist.domain.category.dto.SaveCategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements IDomainMapper<Category, CategoryDTO, SaveCategoryDTO> {
    @Override
    public CategoryDTO mapFromEntity(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName()
        );
    }

    @Override
    public Category mapToEntity(SaveCategoryDTO saveCategoryDTO) {
        return Category.builder()
                .name(saveCategoryDTO.name())
                .build();
    }
}
