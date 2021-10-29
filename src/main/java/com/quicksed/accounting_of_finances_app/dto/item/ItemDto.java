package com.quicksed.accounting_of_finances_app.dto.item;

import lombok.Getter;

import java.time.Instant;
import java.util.Date;

@Getter
public class ItemDto {

    private final int id;
    private final String name;
    private final Instant date;
    private final Double value;
    private final String comment;
    private final Integer accountId;
    private final Integer categoryId;

    public ItemDto(int id, String name, Instant date, Double value, String comment, Integer accountId, Integer categoryId) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.value = value;
        this.comment = comment;
        this.accountId = accountId;
        this.categoryId = categoryId;
    }
}
