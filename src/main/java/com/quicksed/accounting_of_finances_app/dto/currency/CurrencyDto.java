package com.quicksed.accounting_of_finances_app.dto.currency;

import lombok.Getter;

@Getter
public class CurrencyDto {

    private int id;
    private String name;
    private String description;
    private Integer userId;

    public CurrencyDto(int id, String name, String description, Integer userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = userId;
    }
}
