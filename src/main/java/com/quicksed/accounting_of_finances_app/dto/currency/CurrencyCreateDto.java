package com.quicksed.accounting_of_finances_app.dto.currency;

import lombok.Getter;

@Getter
public class CurrencyCreateDto {

    private final String name;
    private final String description;

    public CurrencyCreateDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
