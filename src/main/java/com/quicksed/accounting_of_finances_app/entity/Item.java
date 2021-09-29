package com.quicksed.accounting_of_finances_app.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Item {

    private int id;
    private String name;
    private Date date;
    private Double value;
    private String comment;
    private Account account;
    private Category category;

    public Item(Account account, Category category) {
        this.account = account;
        this.category = category;
    }
}
