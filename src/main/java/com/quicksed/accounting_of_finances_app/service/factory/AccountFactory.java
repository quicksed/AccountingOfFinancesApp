package com.quicksed.accounting_of_finances_app.service.factory;

import com.quicksed.accounting_of_finances_app.entity.Account;
import com.quicksed.accounting_of_finances_app.entity.Currency;
import com.quicksed.accounting_of_finances_app.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AccountFactory {

    public Account build(String name, String description, User user, Currency currency){
        return new Account(name, description, user, currency);
    }
}
