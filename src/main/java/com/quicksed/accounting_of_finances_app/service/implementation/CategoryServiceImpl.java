package com.quicksed.accounting_of_finances_app.service.implementation;

import com.quicksed.accounting_of_finances_app.dto.category.CategoryCreateDto;
import com.quicksed.accounting_of_finances_app.dto.category.CategoryDto;
import com.quicksed.accounting_of_finances_app.dto.category.CategoryUpdateDto;
import com.quicksed.accounting_of_finances_app.entity.Category;
import com.quicksed.accounting_of_finances_app.repository.CategoryRepository;
import com.quicksed.accounting_of_finances_app.service.CategoryService;
import com.quicksed.accounting_of_finances_app.service.factory.CategoryFactory;
import com.quicksed.accounting_of_finances_app.service.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryFactory categoryFactory;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper, CategoryFactory categoryFactory) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.categoryFactory = categoryFactory;
    }

    @Retryable(IllegalArgumentException.class)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public CategoryDto createCategory(CategoryCreateDto categoryCreateDto) {

        Category category = categoryFactory.build(
                categoryCreateDto.getName(),
                categoryCreateDto.getCategoryType().getId(),
                categoryCreateDto.getUserId()
        );

        category = categoryRepository.saveAndFlush(category);
        return categoryMapper.mapCategoryToCategoryDto(category);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public CategoryDto getCategory(int id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        return categoryMapper.mapCategoryToCategoryDto(category);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public List<CategoryDto> getUsersCategories(int userId) {
        List<Category> categories = categoryRepository.findByUserId(userId);
        return categoryMapper.mapCategoryToCategoryDto(categories);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.mapCategoryToCategoryDto(categories);
    }

    @Retryable(IllegalArgumentException.class)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public CategoryDto updateCategory(int id, CategoryUpdateDto categoryUpdateDto) {
        Category category = categoryRepository.findById(id).orElseThrow();

        category.setName(categoryUpdateDto.getName());
        category.setCategoryType(categoryUpdateDto.getCategoryType().getId());

        categoryRepository.saveAndFlush(category);
        return categoryMapper.mapCategoryToCategoryDto(category);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void deleteCategory(int id) {
        Category category = categoryRepository.findById(id).orElseThrow();

        categoryRepository.delete(category);
    }
}
