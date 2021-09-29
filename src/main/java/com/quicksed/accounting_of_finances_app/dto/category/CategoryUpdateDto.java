package com.quicksed.accounting_of_finances_app.dto.category;

import com.quicksed.accounting_of_finances_app.enums.CategoryType;
import lombok.Getter;

@Getter
public class CategoryUpdateDto {

    private final String name;
    private final CategoryType categoryType;

    public CategoryUpdateDto(String name, CategoryType categoryType) {
        this.name = name;
        this.categoryType = categoryType;
    }
}
