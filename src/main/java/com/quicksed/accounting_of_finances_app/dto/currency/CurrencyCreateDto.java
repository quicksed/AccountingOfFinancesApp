package com.quicksed.accounting_of_finances_app.dto.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CurrencyCreateDto {

    private final String name;
    private final String description;
    private final Integer userId;

    public CurrencyCreateDto(@JsonProperty("name") String name,
                             @JsonProperty("description") String description,
                             @JsonProperty("userId") Integer userId) {
        this.name = name;
        this.description = description;
        this.userId = userId;
    }
}
