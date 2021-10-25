package com.quicksed.accounting_of_finances_app.service.factory;

import com.quicksed.accounting_of_finances_app.entity.Currency;
import com.quicksed.accounting_of_finances_app.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CurrencyFactory {

    public Currency build(String name, String description, User user) {
        return new Currency(name, description, user);
    }
}
