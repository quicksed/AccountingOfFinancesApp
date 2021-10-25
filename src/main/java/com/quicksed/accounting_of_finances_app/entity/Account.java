package com.quicksed.accounting_of_finances_app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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

    @OneToMany(mappedBy = "account")
    private Set<Item> items;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "currency_id", updatable = false)
    private Currency currency;

    public Account() {

    }

    public Account(String name, String description, User user, Currency currency) {
        this.name = name;
        this.description = description;
        this.user = user;
        this.currency = currency;
    }
}
