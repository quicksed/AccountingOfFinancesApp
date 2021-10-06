package com.quicksed.accounting_of_finances_app.dto.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CurrencyUpdateDto {

    private String description;

    public CurrencyUpdateDto(@JsonProperty("description") String description) {
        this.description = description;
    }
}
