package com.quicksed.accounting_of_finances_app.controller;

import com.quicksed.accounting_of_finances_app.annotation.Loggable;
import com.quicksed.accounting_of_finances_app.dto.category.CategoryCreateDto;
import com.quicksed.accounting_of_finances_app.dto.category.CategoryDto;
import com.quicksed.accounting_of_finances_app.dto.category.CategoryUpdateDto;
import com.quicksed.accounting_of_finances_app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Loggable
    @GetMapping("/getCategory/{id}")
    public CategoryDto getCategory(int id) {
        return categoryService.getCategory(id);
    }

    @Loggable
    @GetMapping("/getUsersCategory/user/{userId}")
    public List<CategoryDto> getUsersCategory(int userId) {
        return categoryService.getUsersCategories(userId);
    }

    @Loggable
    @GetMapping("/getAllCategories")
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @Loggable
    @PostMapping("/createCategory")
    public CategoryDto createCategory(@RequestBody CategoryCreateDto categoryCreateDto) {
        return categoryService.createCategory(categoryCreateDto);
    }

    @Loggable
    @PutMapping("/update/category/{id}")
    public CategoryDto updateCategory(@RequestBody CategoryUpdateDto categoryUpdateDto,
                               @PathVariable("id") Integer categoryId) {

        return categoryService.updateCategory(categoryId, categoryUpdateDto);
    }

    @Loggable
    @DeleteMapping("/delete/category/{id}")
    public void deleteCategory(int id) {
        categoryService.deleteCategory(id);
    }
}
