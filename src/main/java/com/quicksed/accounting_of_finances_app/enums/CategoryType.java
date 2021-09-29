package com.quicksed.accounting_of_finances_app.enums;

import lombok.Getter;

@Getter
public enum CategoryType {
    Income(1, "Income"),
    Consumption(2, "Consumption");

    private int id;
    private String categoryTypeName;

    CategoryType(int id, String categoryTypeName) {
        this.id = id;
        this.categoryTypeName = categoryTypeName;
    }
}
