package com.wallet_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet_api.dto.CategoryRequest;
import com.wallet_api.dto.CategoryResponse;
import com.wallet_api.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory (@Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse create = categoryService.createCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getMyCategories () {
        List<CategoryResponse> listCategoryResponses = categoryService.getMyCategories();
        return ResponseEntity.ok(listCategoryResponses);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById (@PathVariable Long id) {
        CategoryResponse getCategoryResponse = categoryService.getCategoryById(id);
        return ResponseEntity.ok(getCategoryResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> putCategoryById (@PathVariable Long id, @Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse putCategoryResponse = categoryService.putCategoryById(id, categoryRequest);
        return ResponseEntity.ok(putCategoryResponse);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById (@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }

}
