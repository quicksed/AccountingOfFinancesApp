package com.quicksed.accounting_of_finances_app.service.factory;

import com.quicksed.accounting_of_finances_app.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountFactory {

    public Account build(String name, String description, Integer userId, Integer currencyId){
        return new Account(name, description, userId, currencyId);
    }
}
