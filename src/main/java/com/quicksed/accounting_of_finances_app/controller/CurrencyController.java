package com.quicksed.accounting_of_finances_app.controller;

import com.quicksed.accounting_of_finances_app.annotation.Loggable;
import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyCreateDto;
import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyDto;
import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyUpdateDto;
import com.quicksed.accounting_of_finances_app.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Loggable
    @GetMapping("/getCurrency/{id}")
    public CurrencyDto getCurrency(int id) {
        return currencyService.getCurrency(id);
    }

    @Loggable
    @GetMapping("/getAllCurrencies")
    public List<CurrencyDto> getAllCurrencies() {
        return currencyService.getAllCurrency();
    }

    @Loggable
    @PostMapping("/createCurrency")
    public CurrencyDto createCurrency(@RequestBody CurrencyCreateDto currencyCreateDto) {
        return currencyService.createCurrency(currencyCreateDto);
    }

    @Loggable
    @PutMapping("/update/currency/{id}")
    public CurrencyDto updateCurrency(@RequestBody CurrencyUpdateDto currencyUpdateDto,
                                      @PathVariable("id") Integer currencyId) {

        return currencyService.updateCurrency(currencyId, currencyUpdateDto);
    }

    @Loggable
    @DeleteMapping("/delete/currency/{id}")
    public void deleteCurrency(int id) {
        currencyService.deleteCurrency(id);
    }
}
