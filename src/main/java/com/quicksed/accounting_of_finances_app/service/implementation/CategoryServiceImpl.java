package com.quicksed.accounting_of_finances_app.service.implementation;

import com.quicksed.accounting_of_finances_app.dto.category.CategoryCreateDto;
import com.quicksed.accounting_of_finances_app.dto.category.CategoryDto;
import com.quicksed.accounting_of_finances_app.dto.category.CategoryUpdateDto;
import com.quicksed.accounting_of_finances_app.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public void createCategory(CategoryCreateDto category) {

    }

    @Override
    public CategoryDto getCategory(int id) {
        return null;
    }

    @Override
    public List<CategoryDto> getUsersCategories(int userId) {
        return null;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return null;
    }

    @Override
    public boolean updateCategory(int id, CategoryUpdateDto category) {
        return false;
    }

    @Override
    public boolean deleteCategory(int id) {
        return false;
    }
}
