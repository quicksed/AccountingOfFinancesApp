package com.quicksed.accounting_of_finances_app.dto.account;

import com.quicksed.accounting_of_finances_app.dto.user.UserDto;
import lombok.Getter;

@Getter
public class AccountDto {

    private final int id;
    private final String name;
    private final String description;
    private final UserDto user;

    public AccountDto(int id, String name, String description, UserDto user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
    }
}
