package com.quicksed.accounting_of_finances_app.service.factory;

import com.quicksed.accounting_of_finances_app.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryFactory {

    public Category build(String name, Integer categoryType, Integer userId) {
        return new Category(name, categoryType, userId);
    }
}
