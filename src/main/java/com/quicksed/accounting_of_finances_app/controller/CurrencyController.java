package com.quicksed.accounting_of_finances_app.controller;

import com.quicksed.accounting_of_finances_app.annotation.Loggable;
import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyCreateDto;
import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyDto;
import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyUpdateDto;
import com.quicksed.accounting_of_finances_app.service.CurrencyService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Loggable
@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/{id}")
    public CurrencyDto getCurrency(@PathVariable("id") int id) throws NotFoundException {
        return currencyService.getCurrency(id);
    }

    @GetMapping("/")
    public List<CurrencyDto> getAllCurrencies() {
        return currencyService.getAllCurrency();
    }

    @PostMapping("/")
    public CurrencyDto createCurrency(@RequestBody CurrencyCreateDto currencyCreateDto) throws NotFoundException {
        return currencyService.createCurrency(currencyCreateDto);
    }

    @PutMapping("/{id}")
    public CurrencyDto updateCurrency(@RequestBody CurrencyUpdateDto currencyUpdateDto,
                                      @PathVariable("id") Integer currencyId) throws NotFoundException {

        return currencyService.updateCurrency(currencyId, currencyUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCurrency(@PathVariable("id") int id) throws NotFoundException {
        currencyService.deleteCurrency(id);
    }
}
