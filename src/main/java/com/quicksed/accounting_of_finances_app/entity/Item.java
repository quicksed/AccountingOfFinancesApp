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

    private Double value;

    private String comment;

    private Integer accountId;

    private Integer categoryId;

    public Item() {

    }

    public Item(String name, Date date, Double value, String comment, Integer accountId, Integer categoryId) {
        this.name = name;
        this.date = date;
        this.value = value;
        this.comment = comment;
        this.accountId = accountId;
        this.categoryId = categoryId;
    }
}
