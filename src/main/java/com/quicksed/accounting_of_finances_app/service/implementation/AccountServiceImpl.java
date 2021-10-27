package com.quicksed.accounting_of_finances_app.service.implementation;

import com.quicksed.accounting_of_finances_app.dto.account.AccountCreateDto;
import com.quicksed.accounting_of_finances_app.dto.account.AccountDto;
import com.quicksed.accounting_of_finances_app.dto.account.AccountUpdateDto;
import com.quicksed.accounting_of_finances_app.entity.Account;
import com.quicksed.accounting_of_finances_app.entity.Currency;
import com.quicksed.accounting_of_finances_app.entity.User;
import com.quicksed.accounting_of_finances_app.helper.OptionalChecker;
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
        Optional<User> userOptional = userRepository.findById(accountCreateDto.getUserId());
        Optional<Currency> currencyOptional = currencyRepository.findById(accountCreateDto.getUserId());

        User user = OptionalChecker.checkOptional(userOptional);
        Currency currency = OptionalChecker.checkOptional(currencyOptional);

        Account account = accountFactory.build(
                accountCreateDto.getName(),
                accountCreateDto.getDescription(),
                user,
                currency
        );

        account = accountRepository.save(account);
        return accountMapper.mapAccountToAccountDto(account);
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public AccountDto getAccountById(int id) throws NotFoundException {
        Optional<Account> accountOptional = accountRepository.findById(id);
        Account account = OptionalChecker.checkOptional(accountOptional);

        return accountMapper.mapAccountToAccountDto(account);
    }

    @Transactional
    @Override
    public List<AccountDto> getUsersAccounts(int userId) {
        List<Account> accounts = accountRepository.findByUserId(userId);
        return accountMapper.mapAccountToAccountDto(accounts);
    }

    @Override
    public List<AccountDto> getUsersAccounts(String userEmail) throws NotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        User user = OptionalChecker.checkOptional(userOptional);

        List<Account> accounts = accountRepository.findByUserId(user.getId());
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
        Account account = OptionalChecker.checkOptional(accountOptional);

        account.setName(accountUpdate.getName());
        account.setDescription(accountUpdate.getDescription());

        accountRepository.saveAndFlush(account);
        return accountMapper.mapAccountToAccountDto(account);
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public void deleteAccount(int id) throws NotFoundException {
        Optional<Account> accountOptional = accountRepository.findById(id);
        Account account = OptionalChecker.checkOptional(accountOptional);

        accountRepository.delete(account);
    }
}
