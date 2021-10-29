package com.quicksed.accounting_of_finances_app.service.implementation;

import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyCreateDto;
import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyDto;
import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyUpdateDto;
import com.quicksed.accounting_of_finances_app.entity.Currency;
import com.quicksed.accounting_of_finances_app.entity.User;
import com.quicksed.accounting_of_finances_app.helper.OptionalChecker;
import com.quicksed.accounting_of_finances_app.repository.CurrencyRepository;
import com.quicksed.accounting_of_finances_app.repository.UserRepository;
import com.quicksed.accounting_of_finances_app.service.CurrencyService;
import com.quicksed.accounting_of_finances_app.service.factory.CurrencyFactory;
import com.quicksed.accounting_of_finances_app.service.mapper.CurrencyMapper;
import javassist.NotFoundException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final UserRepository userRepository;

    private final CurrencyMapper currencyMapper;
    private final CurrencyFactory currencyFactory;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, UserRepository userRepository,
                               CurrencyMapper currencyMapper, CurrencyFactory currencyFactory) {
        this.currencyRepository = currencyRepository;
        this.userRepository = userRepository;
        this.currencyMapper = currencyMapper;
        this.currencyFactory = currencyFactory;
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public CurrencyDto createCurrency(CurrencyCreateDto currencyCreateDto) throws NotFoundException {
        Optional<User> userOptional = userRepository.findById(currencyCreateDto.getUserId());
        User user = OptionalChecker.checkOptional(userOptional);

        Currency currency = currencyFactory.build(
                currencyCreateDto.getName(),
                currencyCreateDto.getDescription(),
                user
        );

        currency = currencyRepository.save(currency);
        return currencyMapper.mapCurrencyToCurrencyDto(currency);
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public CurrencyDto getCurrency(int id) throws NotFoundException {
        Optional<Currency> currencyOptional = currencyRepository.findById(id);
        Currency currency = OptionalChecker.checkOptional(currencyOptional);

        return currencyMapper.mapCurrencyToCurrencyDto(currency);
    }

    @Transactional
    @Override
    public List<CurrencyDto> getAllCurrency() {
        List<Currency> currencies = currencyRepository.findAll();
        return currencyMapper.mapCurrencyToCurrencyDto(currencies);
    }

    @Retryable(IllegalArgumentException.class)
    @Transactional
    @Override
    public CurrencyDto updateCurrency(int id, CurrencyUpdateDto currencyUpdateDto) throws NotFoundException {
        Optional<Currency> currencyOptional = currencyRepository.findById(id);
        Currency currency = OptionalChecker.checkOptional(currencyOptional);

        currency.setDescription(currencyUpdateDto.getDescription());

        currencyRepository.saveAndFlush(currency);
        return currencyMapper.mapCurrencyToCurrencyDto(currency);
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public void deleteCurrency(int id) throws NotFoundException {
        Optional<Currency> currencyOptional = currencyRepository.findById(id);
        Currency currency = OptionalChecker.checkOptional(currencyOptional);

        currencyRepository.delete(currency);
    }
}
