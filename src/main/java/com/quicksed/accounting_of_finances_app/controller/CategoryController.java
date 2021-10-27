package com.quicksed.accounting_of_finances_app.controller;

import com.quicksed.accounting_of_finances_app.annotation.Loggable;
import com.quicksed.accounting_of_finances_app.dto.category.CategoryCreateDto;
import com.quicksed.accounting_of_finances_app.dto.category.CategoryDto;
import com.quicksed.accounting_of_finances_app.dto.category.CategoryUpdateDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserWithRolesDto;
import com.quicksed.accounting_of_finances_app.helper.RoleChecker;
import com.quicksed.accounting_of_finances_app.service.CategoryService;
import com.quicksed.accounting_of_finances_app.service.UserService;
import javassist.NotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Loggable
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final UserService userService;

    public CategoryController(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable("id") int id) throws NotFoundException, AccessDeniedException {
        if (!RoleChecker.isAdminUser()) {
            isAvailableCategoryToThisUser(id);
        }

        return categoryService.getCategoryById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/getCategoriesByUserId/{userId}")
    public List<CategoryDto> getUsersCategory(@PathVariable("userId") int userId) throws NotFoundException {
        if (!RoleChecker.isAdminUser()) {
            isAvailableCategoriesToThisUser(userId);
        }
        return categoryService.getUsersCategories(userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/")
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/")
    public CategoryDto createCategory(@RequestBody CategoryCreateDto categoryCreateDto) throws NotFoundException {
        return categoryService.createCategory(categoryCreateDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping("/{id}")
    public CategoryDto updateCategory(@RequestBody CategoryUpdateDto categoryUpdateDto,
                                      @PathVariable("id") Integer id) throws NotFoundException, AccessDeniedException {
        if (!RoleChecker.isAdminUser()) {
            isAvailableCategoryToThisUser(id);
        }

        return categoryService.updateCategory(id, categoryUpdateDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") int id) throws NotFoundException {
        if (!RoleChecker.isAdminUser()) {
            isAvailableCategoryToThisUser(id);
        }

        categoryService.deleteCategory(id);
    }

    private void isAvailableCategoryToThisUser(int categoryId) throws NotFoundException {
        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        UserWithRolesDto user = userService.getUserByEmail(authenticatedUserEmail);
        CategoryDto account = categoryService.getCategoryById(categoryId);

        if (account.getUserId() != user.getId()){
            throw new AccessDeniedException("Access denied!");
        }
    }

    private void isAvailableCategoriesToThisUser(int userId) throws NotFoundException {
        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserWithRolesDto user = userService.getUserById(userId);

        if (!authenticatedUserEmail.equals(user.getEmail())){
            throw new AccessDeniedException("Access denied!");
        }
    }
}
