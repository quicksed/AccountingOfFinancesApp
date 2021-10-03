package com.quicksed.accounting_of_finances_app.dto.account;

import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserDto;
import com.quicksed.accounting_of_finances_app.entity.Currency;
import lombok.Getter;

@Getter
public class AccountCreateDto {

    private final String name;
    private final String description;
    private final UserDto user;
    private final CurrencyDto currency;

    public AccountCreateDto(String name, String description, UserDto user, CurrencyDto currency) {
        this.name = name;
        this.description = description;
        this.user = user;
        this.currency = currency;
    }
}
