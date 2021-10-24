package com.quicksed.accounting_of_finances_app.dto.account;

import lombok.Getter;

@Getter
public class AccountDto {

    private final int id;
    private final String name;
    private final String description;

    public AccountDto(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
