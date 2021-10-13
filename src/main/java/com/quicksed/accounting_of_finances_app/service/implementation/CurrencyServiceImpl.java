package com.quicksed.accounting_of_finances_app.service.implementation;

import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyCreateDto;
import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyDto;
import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyUpdateDto;
import com.quicksed.accounting_of_finances_app.entity.Currency;
import com.quicksed.accounting_of_finances_app.repository.CurrencyRepository;
import com.quicksed.accounting_of_finances_app.service.CurrencyService;
import com.quicksed.accounting_of_finances_app.service.factory.CurrencyFactory;
import com.quicksed.accounting_of_finances_app.service.mapper.CurrencyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;
    private final CurrencyFactory currencyFactory;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository, CurrencyMapper currencyMapper, CurrencyFactory currencyFactory) {
        this.currencyRepository = currencyRepository;
        this.currencyMapper = currencyMapper;
        this.currencyFactory = currencyFactory;
    }

    @Retryable(IllegalArgumentException.class)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public CurrencyDto createCurrency(CurrencyCreateDto currencyCreateDto) {

        Currency currency = currencyFactory.build(
                currencyCreateDto.getName(),
                currencyCreateDto.getDescription()
        );

        currency = currencyRepository.saveAndFlush(currency);
        return currencyMapper.mapCurrencyToCurrencyDto(currency);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public CurrencyDto getCurrency(int id) {
        Currency currency = currencyRepository.findById(id).orElseThrow();
        return currencyMapper.mapCurrencyToCurrencyDto(currency);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public List<CurrencyDto> getAllCurrency() {
        List<Currency> currencies = currencyRepository.findAll();
        return currencyMapper.mapCurrencyToCurrencyDto(currencies);
    }

    @Retryable(IllegalArgumentException.class)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public CurrencyDto updateCurrency(int id, CurrencyUpdateDto currencyUpdateDto) {
        Currency currency = currencyRepository.findById(id).orElseThrow();

        currency.setDescription(currencyUpdateDto.getDescription());

        currencyRepository.saveAndFlush(currency);
        return currencyMapper.mapCurrencyToCurrencyDto(currency);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void deleteCurrency(int id) {
        Currency currency = currencyRepository.findById(id).orElseThrow();

        currencyRepository.delete(currency);
    }
}
