package com.quicksed.accounting_of_finances_app.service.implementation;

import com.quicksed.accounting_of_finances_app.dto.category.CategoryCreateDto;
import com.quicksed.accounting_of_finances_app.dto.category.CategoryDto;
import com.quicksed.accounting_of_finances_app.dto.category.CategoryUpdateDto;
import com.quicksed.accounting_of_finances_app.entity.Account;
import com.quicksed.accounting_of_finances_app.entity.Category;
import com.quicksed.accounting_of_finances_app.entity.User;
import com.quicksed.accounting_of_finances_app.helper.OptionalChecker;
import com.quicksed.accounting_of_finances_app.repository.CategoryRepository;
import com.quicksed.accounting_of_finances_app.repository.UserRepository;
import com.quicksed.accounting_of_finances_app.service.CategoryService;
import com.quicksed.accounting_of_finances_app.service.factory.CategoryFactory;
import com.quicksed.accounting_of_finances_app.service.mapper.CategoryMapper;
import javassist.NotFoundException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    private final CategoryMapper categoryMapper;
    private final CategoryFactory categoryFactory;

    public CategoryServiceImpl(CategoryRepository categoryRepository, UserRepository userRepository,
                               CategoryMapper categoryMapper, CategoryFactory categoryFactory) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.categoryMapper = categoryMapper;
        this.categoryFactory = categoryFactory;
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public CategoryDto createCategory(CategoryCreateDto categoryCreateDto) throws NotFoundException {
        Optional<User> userOptional = userRepository.findById(categoryCreateDto.getUserId());
        User user = OptionalChecker.checkOptional(userOptional);

        Category category = categoryFactory.build(
                categoryCreateDto.getName(),
                categoryCreateDto.getCategoryType(),
                user
        );

        category = categoryRepository.saveAndFlush(category);
        return categoryMapper.mapCategoryToCategoryDto(category);
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public CategoryDto getCategoryById(int id) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        Category category = OptionalChecker.checkOptional(categoryOptional);

        return categoryMapper.mapCategoryToCategoryDto(category);
    }

    @Transactional
    @Override
    public List<CategoryDto> getUsersCategories(int userId) {
        List<Category> categories = categoryRepository.findByUserId(userId);
        return categoryMapper.mapCategoryToCategoryDto(categories);
    }

    @Override
    public List<CategoryDto> getUsersCategories(String userEmail) throws NotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        User user = OptionalChecker.checkOptional(userOptional);

        List<Category> categories = categoryRepository.findByUserId(user.getId());
        return categoryMapper.mapCategoryToCategoryDto(categories);
    }

    @Transactional
    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.mapCategoryToCategoryDto(categories);
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public CategoryDto updateCategory(int id, CategoryUpdateDto categoryUpdateDto) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        Category category = OptionalChecker.checkOptional(categoryOptional);

        category.setName(categoryUpdateDto.getName());
        category.setCategoryType(categoryUpdateDto.getCategoryType());

        categoryRepository.saveAndFlush(category);
        return categoryMapper.mapCategoryToCategoryDto(category);
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public void deleteCategory(int id) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        Category category = OptionalChecker.checkOptional(categoryOptional);

        categoryRepository.delete(category);
    }
}
