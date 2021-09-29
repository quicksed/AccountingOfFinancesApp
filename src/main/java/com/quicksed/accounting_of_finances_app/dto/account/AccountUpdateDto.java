package com.quicksed.accounting_of_finances_app.dto.account;

import lombok.Getter;

@Getter
public class AccountUpdateDto {

    private final String name;
    private final String description;

    public AccountUpdateDto(int id, String name, String description) {
        this.name = name;
        this.description = description;
    }
}
