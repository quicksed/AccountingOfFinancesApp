package com.quicksed.accounting_of_finances_app.controller;

import com.quicksed.accounting_of_finances_app.annotation.Loggable;
import com.quicksed.accounting_of_finances_app.dto.category.CategoryCreateDto;
import com.quicksed.accounting_of_finances_app.dto.category.CategoryDto;
import com.quicksed.accounting_of_finances_app.dto.category.CategoryUpdateDto;
import com.quicksed.accounting_of_finances_app.service.CategoryService;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Loggable
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable("id") int id) throws NotFoundException {
        return categoryService.getCategory(id);
    }

    @GetMapping("/getCategoriesByUserId/{userId}")
    public List<CategoryDto> getUsersCategory(@PathVariable("userId") int userId) {
        return categoryService.getUsersCategories(userId);
    }

    @GetMapping("/")
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/")
    public CategoryDto createCategory(@RequestBody CategoryCreateDto categoryCreateDto) throws NotFoundException {
        return categoryService.createCategory(categoryCreateDto);
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@RequestBody CategoryUpdateDto categoryUpdateDto,
                                      @PathVariable("id") Integer categoryId) throws NotFoundException {

        return categoryService.updateCategory(categoryId, categoryUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") int id) throws NotFoundException {
        categoryService.deleteCategory(id);
    }
}
