package com.jeyofdev.shopping_krist.domain.category;

import com.jeyofdev.shopping_krist.domain.category.dto.CategoryDTO;
import com.jeyofdev.shopping_krist.domain.category.dto.SaveCategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAllCategory() {
        List<Category> categoryList = categoryService.findAll();
        List<CategoryDTO> categoryDTOList = categoryList.stream().map(categoryMapper::mapFromEntity).toList();

        return new ResponseEntity<>(categoryDTOList, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable("categoryId") UUID categoryId) {
        Category category = categoryService.findById(categoryId);
        CategoryDTO categoryDTO = categoryMapper.mapFromEntity(category);

        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CategoryDTO> saveCategory(
            @RequestBody SaveCategoryDTO saveCategoryDTO
    ) {
        Category category = categoryMapper.mapToEntity(saveCategoryDTO);
        Category newCategory = categoryService.save(category);
        CategoryDTO newCategoryDTO = categoryMapper.mapFromEntity(newCategory);

        return new ResponseEntity<>(newCategoryDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategoryById(
            @PathVariable("categoryId") UUID categoryId,
            @RequestBody SaveCategoryDTO saveCategoryDTO
    ) {
        Category category = categoryMapper.mapToEntity(saveCategoryDTO);
        Category updateCategory = categoryService.updateById(categoryId, category);
        CategoryDTO updateCategoryDTO = categoryMapper.mapFromEntity(updateCategory);

        return new ResponseEntity<>(updateCategoryDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable("categoryId") UUID categoryId) {
        categoryService.deleteById(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
