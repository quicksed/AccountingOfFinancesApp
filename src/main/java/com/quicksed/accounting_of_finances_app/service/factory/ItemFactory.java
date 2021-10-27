package com.quicksed.accounting_of_finances_app.service.factory;

import com.quicksed.accounting_of_finances_app.entity.Account;
import com.quicksed.accounting_of_finances_app.entity.Category;
import com.quicksed.accounting_of_finances_app.entity.Item;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Component
public class ItemFactory {

    public Item build(String name, Instant date, Double value, String comment, Account account, Category category) {
        return new Item(name, date, value, comment, account, category);
    }
}
