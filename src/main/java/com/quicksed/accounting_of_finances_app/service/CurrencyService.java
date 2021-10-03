package com.quicksed.accounting_of_finances_app.service;

import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyCreateDto;
import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyDto;
import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyUpdateDto;

import java.util.List;

public interface CurrencyService {

    void createCurrency(CurrencyCreateDto currency);

    CurrencyDto getCurrency(int id);

    List<CurrencyDto> getAllCurrency();

    boolean updateCurrency(int id, CurrencyUpdateDto currency);

    boolean deleteCurrency(int id);
}
