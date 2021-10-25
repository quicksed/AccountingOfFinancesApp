package com.quicksed.accounting_of_finances_app.entity;

import com.quicksed.accounting_of_finances_app.enums.CategoryType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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

    private CategoryType categoryType;

    @OneToMany(mappedBy = "category")
    private Set<Item> items;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    public Category() {

    }

    public Category(String name, CategoryType categoryType, User user) {
        this.name = name;
        this.categoryType = categoryType;
        this.user = user;
    }
}
