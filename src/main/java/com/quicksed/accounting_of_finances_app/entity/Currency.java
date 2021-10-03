package com.quicksed.accounting_of_finances_app.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Currency {

    private int id;
    private String name;
    private String description;

    public Currency(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
