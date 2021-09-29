package com.quicksed.accounting_of_finances_app.entity;

import com.quicksed.accounting_of_finances_app.enums.CategoryType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {

    private int id;
    private String name;
    private CategoryType categoryType;
    private User user;
}
