package com.quicksed.accounting_of_finances_app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Currency {

    @Id
    @SequenceGenerator(name = "currency_id_seq_generator",
            sequenceName = "currency_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "currency_id_seq_generator")
    private int id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "currency")
    private Set<Account> accounts;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    public Currency() {

    }

    public Currency(String name, String description, User user) {
        this.name = name;
        this.description = description;
        this.user = user;
    }
}
