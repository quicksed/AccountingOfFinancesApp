package com.quicksed.accounting_of_finances_app.service;

import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyCreateDto;
import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyDto;
import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyUpdateDto;
import javassist.NotFoundException;

import java.util.List;

public interface CurrencyService {

    CurrencyDto createCurrency(CurrencyCreateDto currency) throws NotFoundException;

    CurrencyDto getCurrency(int id) throws NotFoundException;

    List<CurrencyDto> getAllCurrency();

    CurrencyDto updateCurrency(int id, CurrencyUpdateDto currency) throws NotFoundException;

    void deleteCurrency(int id) throws NotFoundException;
}
