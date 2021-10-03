package com.quicksed.accounting_of_finances_app.dto.currency;

import lombok.Getter;

@Getter
public class CurrencyDto {

    private int id;
    private String name;
    private String description;

    public CurrencyDto(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
