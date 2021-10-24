package com.quicksed.accounting_of_finances_app.service.implementation;

import com.quicksed.accounting_of_finances_app.dto.account.AccountCreateDto;
import com.quicksed.accounting_of_finances_app.dto.account.AccountDto;
import com.quicksed.accounting_of_finances_app.dto.account.AccountUpdateDto;
import com.quicksed.accounting_of_finances_app.entity.Account;
import com.quicksed.accounting_of_finances_app.repository.AccountRepository;
import com.quicksed.accounting_of_finances_app.service.AccountService;
import com.quicksed.accounting_of_finances_app.service.factory.AccountFactory;
import com.quicksed.accounting_of_finances_app.service.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AccountFactory accountFactory;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, AccountFactory accountFactory) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.accountFactory = accountFactory;
    }

    @Override
    public AccountDto createAccount(AccountCreateDto accountCreateDto) {

        Account account = accountFactory.build(
                accountCreateDto.getName(),
                accountCreateDto.getDescription(),
                accountCreateDto.getUserId(),
                accountCreateDto.getCurrencyId()
        );

        account = accountRepository.saveAndFlush(account);
        return accountMapper.mapAccountToAccountDto(account);
    }

    @Override
    public AccountDto getAccountById(int id) {
        Account account = accountRepository.findById(id).orElseThrow();
        return accountMapper.mapAccountToAccountDto(account);
    }

    @Override
    public List<AccountDto> getUsersAccounts(int userId) {
        List<Account> accounts = accountRepository.findByUserId(userId);
        return accountMapper.mapAccountToAccountDto(accounts);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accountMapper.mapAccountToAccountDto(accounts);
    }

    @Override
    public AccountDto updateAccount(int id, AccountUpdateDto accountUpdate) {
        Account account = accountRepository.findById(id).orElseThrow();

        account.setName(accountUpdate.getName());
        account.setDescription(accountUpdate.getDescription());

        accountRepository.saveAndFlush(account);
        return accountMapper.mapAccountToAccountDto(account);
    }

    @Override
    public void deleteAccount(int id) {
        Account account = accountRepository.findById(id).orElseThrow();

        accountRepository.delete(account);
    }
}
