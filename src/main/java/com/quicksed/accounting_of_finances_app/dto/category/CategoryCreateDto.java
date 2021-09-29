package com.quicksed.accounting_of_finances_app.dto.category;

import com.quicksed.accounting_of_finances_app.dto.user.UserDto;
import com.quicksed.accounting_of_finances_app.enums.CategoryType;
import lombok.Getter;

@Getter
public class CategoryCreateDto {

    private final String name;
    private final CategoryType categoryType;
    private final UserDto user;

    public CategoryCreateDto(String name, CategoryType categoryType, UserDto user) {
        this.name = name;
        this.categoryType = categoryType;
        this.user = user;
    }
}