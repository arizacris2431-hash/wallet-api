package com.wallet_api.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.wallet_api.dto.CategoryRequest;
import com.wallet_api.dto.CategoryResponse;
import com.wallet_api.entity.Category;
import com.wallet_api.entity.User;
import com.wallet_api.exception.BadRequestException;
import com.wallet_api.exception.CategoryNotFoundException;
import com.wallet_api.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse createCategory (CategoryRequest categoryRequest) {
        
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Category category = new Category ();
        
        category.setName(categoryRequest.getName());
        category.setType(categoryRequest.getType());
        category.setUser(user);

        Category saveDB = categoryRepository.save(category);

        return CategoryResponse.builder()
            .id(saveDB.getId())
            .name(saveDB.getName())
            .type(saveDB.getType())
            .build();
    }

    public List<CategoryResponse> getMyCategories () {
        
        // porque toca hacer un cast (User) porque getPrincipal() no devuelve un User, sino un Object
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Category> categories = categoryRepository.findByUser(user);

        return categories.stream().map(c -> new CategoryResponse(
            c.getId(),
            c.getName(),
            c.getType()
        ))
        .toList();

    }
    
    public CategoryResponse getCategoryById (Long id) {

        if (id == null|| id <= 0) {
            throw new BadRequestException("The ID cannot be less than or equal to zero.");
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Category category = categoryRepository.findByIdAndUser(id, user).orElseThrow(() -> new CategoryNotFoundException("The category with that ID could not be found."));
        
        return CategoryResponse.builder()
            .id(category.getId())
            .name(category.getName())
            .type(category.getType())
            .build();
    }

    public CategoryResponse putCategoryById (Long id, CategoryRequest newCategoryRequest) {

        if (id == null|| id <= 0) {
            throw new BadRequestException("The ID cannot be less than or equal to zero.");
        }

        // Validar seguridad (saber quién es el usuario actual)
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Buscar la categoría en la base de datos usando el ID (que vino del PathVariable)
        Category category = categoryRepository.findByIdAndUser(id, user).orElseThrow(() -> new CategoryNotFoundException("The category with that ID could not be found"));

        // Modificar los datos de la categoría con lo que llegó en el RequestBody
        category.setName(newCategoryRequest.getName());
        category.setType(newCategoryRequest.getType());

        // Guardar los cambios en la base de datos
        Category saveDB = categoryRepository.save(category);

        // Convertir el resultado en un "Response" (un formato limpio para el cliente)
        return CategoryResponse.builder()
            .id(saveDB.getId())
            .name(saveDB.getName())
            .type(saveDB.getType())
            .build();
        
    }

    public void deleteCategoryById (Long id) {

        if (id == null|| id <= 0) {
            throw new BadRequestException("The ID cannot be less than or equal to zero.");
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Category category = categoryRepository.findByIdAndUser(id, user).orElseThrow(() -> new CategoryNotFoundException("The category with that ID could not be found"));

        categoryRepository.delete(category);

    }
     
}



        // List<CategoryResponse> categoryResponses = new ArrayList<>();

        // for (Category c: categories) {
        //     CategoryResponse category = new CategoryResponse();

        //     category.setId(c.getId());
        //     category.setName(c.getName());
        //     category.setType(c.getType());

        //     categoryResponses.add(category);
        // }

        // return categoryResponses;