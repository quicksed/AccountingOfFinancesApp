package com.quicksed.accounting_of_finances_app.dto.category;

import com.quicksed.accounting_of_finances_app.enums.CategoryType;
import lombok.Getter;

@Getter
public class CategoryCreateDto {

    private final String name;
    private final CategoryType categoryType;
    private final Integer userId;

    public CategoryCreateDto(String name, CategoryType categoryType, Integer userId) {
        this.name = name;
        this.categoryType = categoryType;
        this.userId = userId;
    }
}
