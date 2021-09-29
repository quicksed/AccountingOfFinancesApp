package com.quicksed.accounting_of_finances_app.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

    private int id;
    private String name;
    private String description;
    private User user;
}
