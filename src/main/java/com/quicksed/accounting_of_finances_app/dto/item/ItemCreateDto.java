package com.quicksed.accounting_of_finances_app.dto.item;

import com.quicksed.accounting_of_finances_app.dto.account.AccountDto;
import com.quicksed.accounting_of_finances_app.dto.category.CategoryDto;
import lombok.Getter;

import java.util.Date;

@Getter
public class ItemCreateDto {

    private final String name;
    private final Date date;
    private final Double value;
    private final String comment;
    private final AccountDto account;
    private final CategoryDto category;

    public ItemCreateDto(String name, Date date, Double value, String comment, AccountDto account, CategoryDto category) {
        this.name = name;
        this.date = date;
        this.value = value;
        this.comment = comment;
        this.account = account;
        this.category = category;
    }
}
