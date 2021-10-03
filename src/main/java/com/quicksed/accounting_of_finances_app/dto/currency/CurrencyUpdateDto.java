package com.quicksed.accounting_of_finances_app.dto.currency;

import lombok.Getter;

@Getter
public class CurrencyUpdateDto {

    private String description;

    public CurrencyUpdateDto(String description) {
        this.description = description;
    }
}
