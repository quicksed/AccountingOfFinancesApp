package com.quicksed.accountingOfFinancesApp.enums;

public enum CategoryType {
    Income(1, "Income"),
    Consumption(2, "Consumption");

    private int id;
    private String categoryTypeName;

    CategoryType(int id, String categoryTypeName) {
        this.id = id;
        this.categoryTypeName = categoryTypeName;
    }

    public int getId() {
        return id;
    }

    public String getCategoryTypeName() {
        return categoryTypeName;
    }
}
