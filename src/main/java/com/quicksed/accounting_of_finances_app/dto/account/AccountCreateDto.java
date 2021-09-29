package com.quicksed.accounting_of_finances_app.dto.account;

import com.quicksed.accounting_of_finances_app.dto.user.UserDto;
import lombok.Getter;

@Getter
public class AccountCreateDto {

    private final String name;
    private final String description;
    private final UserDto user;

    public AccountCreateDto(String name, String description, UserDto user) {
        this.name = name;
        this.description = description;
        this.user = user;
    }
}
