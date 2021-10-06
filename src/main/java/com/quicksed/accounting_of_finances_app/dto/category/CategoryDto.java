package com.quicksed.accounting_of_finances_app.dto.category;

import com.quicksed.accounting_of_finances_app.enums.CategoryType;
import lombok.Getter;

@Getter
public class CategoryDto {

    private final int id;
    private final String name;
    private final CategoryType categoryType;
    private final Integer userId;

    public CategoryDto(int id, String name, CategoryType categoryType, Integer userId) {
        this.id = id;
        this.name = name;
        this.categoryType = categoryType;
        this.userId = userId;
    }
}
