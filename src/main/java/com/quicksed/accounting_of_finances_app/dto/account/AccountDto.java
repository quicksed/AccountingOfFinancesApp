package com.quicksed.accounting_of_finances_app.dto.account;

import lombok.Getter;

@Getter
public class AccountDto {

    private final int id;
    private final String name;
    private final String description;
    private final Integer userId;

    public AccountDto(int id, String name, String description, Integer userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = userId;
    }
}
