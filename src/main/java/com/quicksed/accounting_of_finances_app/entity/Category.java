package com.quicksed.accounting_of_finances_app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @SequenceGenerator(name = "category_id_seq_generator",
            sequenceName = "category_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "category_id_seq_generator")
    private int id;

    private String name;

    private Integer categoryType;

    private Integer userId;

    public Category() {

    }

    public Category(String name, Integer categoryType, Integer userId) {
        this.name = name;
        this.categoryType = categoryType;
        this.userId = userId;
    }
}
