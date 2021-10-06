package com.quicksed.accounting_of_finances_app.service;

import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyCreateDto;
import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyDto;
import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyUpdateDto;

import java.util.List;

public interface CurrencyService {

    CurrencyDto createCurrency(CurrencyCreateDto currency);

    CurrencyDto getCurrency(int id);

    List<CurrencyDto> getAllCurrency();

    CurrencyDto updateCurrency(int id, CurrencyUpdateDto currency);

    void deleteCurrency(int id);
}
