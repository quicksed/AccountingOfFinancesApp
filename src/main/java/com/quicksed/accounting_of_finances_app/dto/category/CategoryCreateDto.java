package com.quicksed.accounting_of_finances_app.dto.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quicksed.accounting_of_finances_app.enums.CategoryType;
import lombok.Getter;

@Getter
public class CategoryCreateDto {

    private final String name;
    private final CategoryType categoryType;
    private final Integer userId;

    public CategoryCreateDto(@JsonProperty("name") String name,
                             @JsonProperty("categoryType") CategoryType categoryType,
                             @JsonProperty("userId") Integer userId) {
        this.name = name;
        this.categoryType = categoryType;
        this.userId = userId;
    }
}
