package com.quicksed.accounting_of_finances_app.service;

import com.quicksed.accounting_of_finances_app.dto.category.CategoryCreateDto;
import com.quicksed.accounting_of_finances_app.dto.category.CategoryDto;
import com.quicksed.accounting_of_finances_app.dto.category.CategoryUpdateDto;
import javassist.NotFoundException;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryCreateDto category) throws NotFoundException;

    CategoryDto getCategory(int id) throws NotFoundException;

    List<CategoryDto> getUsersCategories(int userId);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategory(int id, CategoryUpdateDto category) throws NotFoundException;

    void deleteCategory(int id) throws NotFoundException;
}
