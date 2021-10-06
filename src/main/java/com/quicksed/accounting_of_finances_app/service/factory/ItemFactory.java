package com.quicksed.accounting_of_finances_app.service.factory;

import com.quicksed.accounting_of_finances_app.entity.Item;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ItemFactory {

    public Item build(String name, Date date, Double value, String comment, Integer accountId, Integer categoryId) {
        return new Item(name, date, value, comment, accountId, categoryId);
    }
}
