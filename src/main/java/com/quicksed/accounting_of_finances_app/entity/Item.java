package com.quicksed.accounting_of_finances_app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @SequenceGenerator(name = "item_id_seq_generator",
            sequenceName = "item_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "item_id_seq_generator")
    private int id;

    private String name;

    private Date date;

    @Column(columnDefinition = "NUMERIC(10,2)")
    private Double value;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public Item() {

    }

    public Item(String name, Date date, Double value, String comment, Account account, Category category) {
        this.name = name;
        this.date = date;
        this.value = value;
        this.comment = comment;
        this.account = account;
        this.category = category;
    }
}
