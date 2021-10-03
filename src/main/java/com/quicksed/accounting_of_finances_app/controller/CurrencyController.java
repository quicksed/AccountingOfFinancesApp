package com.quicksed.accounting_of_finances_app.controller;

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

    @GetMapping("/getCurrency/{id}")
    public CurrencyDto getCurrency(int id) {
        return currencyService.getCurrency(id);
    }

    @GetMapping("/getAllCurrencies")
    public List<CurrencyDto> getAllCurrencies() {
        return currencyService.getAllCurrency();
    }

    @PostMapping("/createCurrency")
    public void createCurrency(@RequestBody CurrencyCreateDto currencyCreateDto) {

        currencyService.createCurrency(new CurrencyCreateDto(
                currencyCreateDto.getName(),
                currencyCreateDto.getDescription()
        ));
    }

    @PutMapping("/currency/{id}")
    public void updateCurrency(@RequestBody CurrencyUpdateDto currencyUpdateDto,
                              @PathVariable("id") Integer currencyId) {

        currencyService.updateCurrency(currencyId, currencyUpdateDto);
    }

    @DeleteMapping("/delete/currency/{id}")
    public boolean deleteCurrency(int id) {
        return currencyService.deleteCurrency(id);
    }
}
