package com.quicksed.accounting_of_finances_app.service.implementation;

import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyCreateDto;
import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyDto;
import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyUpdateDto;
import com.quicksed.accounting_of_finances_app.service.CurrencyService;

import java.util.List;

public class CurrencyServiceImpl implements CurrencyService {

    @Override
    public void createCurrency(CurrencyCreateDto currency) {

    }

    @Override
    public CurrencyDto getCurrency(int id) {
        return null;
    }

    @Override
    public List<CurrencyDto> getAllCurrency() {
        return null;
    }

    @Override
    public boolean updateCurrency(int id, CurrencyUpdateDto currency) {
        return false;
    }

    @Override
    public boolean deleteCurrency(int id) {
        return false;
    }
}
