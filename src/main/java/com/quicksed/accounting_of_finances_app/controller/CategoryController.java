package com.quicksed.accounting_of_finances_app.controller;

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

    @GetMapping("/getCategory/{id}")
    public CategoryDto getCategory(int id) {
        return categoryService.getCategory(id);
    }

    @GetMapping("/getUsersCategory/user/{userId}")
    public List<CategoryDto> getUsersCategory(int userId) {
        return categoryService.getUsersCategories(userId);
    }

    @GetMapping("/getAllCategories")
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/createCategory")
    public void createCategory(@RequestBody CategoryCreateDto categoryCreateDto) {

        categoryService.createCategory(new CategoryCreateDto(
                categoryCreateDto.getName(),
                categoryCreateDto.getCategoryType(),
                categoryCreateDto.getUser()
        ));
    }

    @PutMapping("/category/{id}")
    public void updateCategory(@RequestBody CategoryUpdateDto categoryUpdateDto,
                              @PathVariable("id") Integer categoryId) {

        categoryService.updateCategory(categoryId, categoryUpdateDto);
    }

    @DeleteMapping("/delete/category/{id}")
    public boolean deleteCategory(int id) {
        return categoryService.deleteCategory(id);
    }
}
