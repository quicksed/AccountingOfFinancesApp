package com.quicksed.accounting_of_finances_app.service;

import com.quicksed.accounting_of_finances_app.dto.category.CategoryCreateDto;
import com.quicksed.accounting_of_finances_app.dto.category.CategoryDto;
import com.quicksed.accounting_of_finances_app.dto.category.CategoryUpdateDto;

import java.util.List;

public interface CategoryService {

    void createCategory(CategoryCreateDto category);

    CategoryDto getCategory(int id);

    List<CategoryDto> getUsersCategories(int userId);

    List<CategoryDto> getAllCategories();

    boolean updateCategory(int id, CategoryUpdateDto category);

    boolean deleteCategory(int id);
}