package com.quicksed.accounting_of_finances_app.service.factory;

import com.quicksed.accounting_of_finances_app.entity.Currency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyFactory {

    public Currency build(String name, String description) {
        return new Currency(name, description);
    }
}
