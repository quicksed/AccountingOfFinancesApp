package com.quicksed.accounting_of_finances_app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @SequenceGenerator(name = "account_id_seq_generator",
            sequenceName = "account_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "account_id_seq_generator")
    private int id;

    private String name;

    private String description;

    private Integer userId;

    private Integer currencyId;

    public Account() {

    }

    public Account(String name, String description, Integer userId, Integer currencyId) {
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.currencyId = currencyId;
    }
}
