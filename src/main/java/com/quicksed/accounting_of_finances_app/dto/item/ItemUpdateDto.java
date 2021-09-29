package com.quicksed.accounting_of_finances_app.dto.item;

import lombok.Getter;

import java.util.Date;

@Getter
public class ItemUpdateDto {

    private final String name;
    private final Date date;
    private final Double value;
    private final String comment;

    public ItemUpdateDto(String name, Date date, Double value, String comment) {
        this.name = name;
        this.date = date;
        this.value = value;
        this.comment = comment;
    }
}
