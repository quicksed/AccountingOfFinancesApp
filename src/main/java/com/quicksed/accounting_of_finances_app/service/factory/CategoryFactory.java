package com.quicksed.accounting_of_finances_app.service.factory;

import com.quicksed.accounting_of_finances_app.entity.Category;
import com.quicksed.accounting_of_finances_app.entity.User;
import com.quicksed.accounting_of_finances_app.enums.CategoryType;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoryFactory {

    public Category build(String name, CategoryType categoryType, User user) {
        return new Category(name, categoryType, user);
    }
}
