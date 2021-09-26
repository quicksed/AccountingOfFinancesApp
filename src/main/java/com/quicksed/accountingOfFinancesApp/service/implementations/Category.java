package com.quicksed.accountingOfFinancesApp.service.implementations;

import com.quicksed.accountingOfFinancesApp.enums.CategoryType;
import com.quicksed.accountingOfFinancesApp.service.interfaces.ICategoryService;
import com.quicksed.accountingOfFinancesApp.service.interfaces.IUserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Scope("prototype")
@Service
public class Category implements ICategoryService {

    private int id;
    private String name;
    private CategoryType categoryType;
    private IUserService user;

    public Category(IUserService user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public void setUser(IUserService user) {
        this.user = user;
    }

    @Override
    public IUserService getUser() {
        return user;
    }
}
