package com.quicksed.accounting_of_finances_app.service.mapper;

import com.quicksed.accounting_of_finances_app.dto.category.CategoryDto;
import com.quicksed.accounting_of_finances_app.entity.Category;
import com.quicksed.accounting_of_finances_app.enums.CategoryType;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public CategoryDto mapCategoryToCategoryDto(Category model) {
        return new CategoryDto(
                model.getId(),
                model.getName(),
                (model.getCategoryType() == 1)
                        ? CategoryType.Income
                        : CategoryType.Consumption,
                model.getUserId()
        );
    }

    public List<CategoryDto> mapCategoryToCategoryDto(Collection<Category> model) {
        return model.stream()
                .map(this::mapCategoryToCategoryDto)
                .collect(Collectors.toList());
    }
}
