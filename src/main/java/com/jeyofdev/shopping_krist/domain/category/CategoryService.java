package com.jeyofdev.shopping_krist.domain.category;

import com.jeyofdev.shopping_krist.core.abstracts.AbstractDomainServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CategoryService extends AbstractDomainServiceBase<Category, CategoryRepository> {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        super(categoryRepository, "category");
        this.categoryRepository = categoryRepository;
    }
    
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateById(UUID categoryId, Category updatedCategory) {
        Category existingCategory = findById(categoryId);
        Category existingCategoryUpdated = Category.builder()
                .id(categoryId)
                .name(updatedCategory.getName() != null ? updatedCategory.getName() : existingCategory.getName())
                .build();

        return categoryRepository.save(existingCategoryUpdated);
    }

    @Transactional
    public void deleteById(UUID categoryId) {
        findById(categoryId);
        categoryRepository.deleteById(categoryId);
    }
}
