package com.quicksed.accounting_of_finances_app.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AccountCreateDto {

    private final String name;
    private final String description;
    private final Integer userId;
    private final Integer currencyId;

    public AccountCreateDto(@JsonProperty("name") String name,
                            @JsonProperty("description") String description,
                            @JsonProperty("userId") Integer userId,
                            @JsonProperty("currencyId") Integer currencyId) {
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.currencyId = currencyId;
    }
}
