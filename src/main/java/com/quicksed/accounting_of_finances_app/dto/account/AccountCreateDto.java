package com.quicksed.accounting_of_finances_app.dto.account;

import lombok.Getter;

@Getter
public class AccountCreateDto {

    private final String name;
    private final String description;
    private final Integer userId;
    private final Integer currencyId;

    public AccountCreateDto(String name, String description, Integer userId, Integer currencyId) {
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.currencyId = currencyId;
    }
}
