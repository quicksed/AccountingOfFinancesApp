package com.quicksed.accounting_of_finances_app.dto.account;

import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserDto;
import lombok.Getter;

@Getter
public class AccountDto {

    private final int id;
    private final String name;
    private final String description;
    private final UserDto user;
    private final CurrencyDto currency;

    public AccountDto(int id, String name, String description, UserDto user, CurrencyDto currency) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
        this.currency = currency;
    }
}
