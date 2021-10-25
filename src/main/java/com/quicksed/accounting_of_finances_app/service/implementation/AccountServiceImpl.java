package com.quicksed.accounting_of_finances_app.service.implementation;

import com.quicksed.accounting_of_finances_app.dto.account.AccountCreateDto;
import com.quicksed.accounting_of_finances_app.dto.account.AccountDto;
import com.quicksed.accounting_of_finances_app.dto.account.AccountUpdateDto;
import com.quicksed.accounting_of_finances_app.entity.Account;
import com.quicksed.accounting_of_finances_app.entity.Currency;
import com.quicksed.accounting_of_finances_app.entity.User;
import com.quicksed.accounting_of_finances_app.repository.AccountRepository;
import com.quicksed.accounting_of_finances_app.repository.CurrencyRepository;
import com.quicksed.accounting_of_finances_app.repository.UserRepository;
import com.quicksed.accounting_of_finances_app.service.AccountService;
import com.quicksed.accounting_of_finances_app.service.factory.AccountFactory;
import com.quicksed.accounting_of_finances_app.service.mapper.AccountMapper;
import javassist.NotFoundException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    private final AccountMapper accountMapper;
    private final AccountFactory accountFactory;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository,
                              CurrencyRepository currencyRepository, AccountMapper accountMapper, AccountFactory accountFactory) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
        this.accountMapper = accountMapper;
        this.accountFactory = accountFactory;
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public AccountDto createAccount(AccountCreateDto accountCreateDto) throws NotFoundException {

        Optional<User> user = userRepository.findById(accountCreateDto.getUserId());
        Optional<Currency> currency = currencyRepository.findById(accountCreateDto.getUserId());

        if (user.isEmpty()) {
            throw new NotFoundException("User not found!");
        }

        if (currency.isEmpty()) {
            throw new NotFoundException("Currency not found!");
        }

        Account account = accountFactory.build(
                accountCreateDto.getName(),
                accountCreateDto.getDescription(),
                user.get(),
                currency.get()
        );

        account = accountRepository.save(account);
        return accountMapper.mapAccountToAccountDto(account);
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public AccountDto getAccountById(int id) throws NotFoundException {
        Optional<Account> account = accountRepository.findById(id);

        if (account.isEmpty()) {
            throw new NotFoundException("Account not found!");
        }

        return accountMapper.mapAccountToAccountDto(account.get());
    }

    @Transactional
    @Override
    public List<AccountDto> getUsersAccounts(int userId) {

        List<Account> accounts = accountRepository.findByUserId(userId);
        return accountMapper.mapAccountToAccountDto(accounts);
    }

    @Transactional
    @Override
    public List<AccountDto> getAllAccounts() {

        List<Account> accounts = accountRepository.findAll();
        return accountMapper.mapAccountToAccountDto(accounts);
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public AccountDto updateAccount(int id, AccountUpdateDto accountUpdate) throws NotFoundException {

        Optional<Account> accountOptional = accountRepository.findById(id);

        if (accountOptional.isEmpty()) {
            throw new NotFoundException("Account not found!");
        }

        Account account = accountOptional.get();
        account.setName(accountUpdate.getName());
        account.setDescription(accountUpdate.getDescription());

        accountRepository.saveAndFlush(account);
        return accountMapper.mapAccountToAccountDto(account);
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public void deleteAccount(int id) throws NotFoundException {

        Optional<Account> account = accountRepository.findById(id);

        if (account.isEmpty()) {
            throw new NotFoundException("Account not found!");
        }

        accountRepository.delete(account.get());
    }
}
