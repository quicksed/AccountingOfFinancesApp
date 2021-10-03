package com.quicksed.accounting_of_finances_app.service.implementation;

import com.quicksed.accounting_of_finances_app.dto.account.AccountCreateDto;
import com.quicksed.accounting_of_finances_app.dto.account.AccountDto;
import com.quicksed.accounting_of_finances_app.dto.account.AccountUpdateDto;
import com.quicksed.accounting_of_finances_app.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public void createAccount(AccountCreateDto account) {

    }

    @Override
    public AccountDto getAccountById(int id) {
        return null;
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return null;
    }

    @Override
    public List<AccountDto> getUsersAccounts(int userId) {
        return null;
    }

    @Override
    public boolean updateAccount(int id, AccountUpdateDto account) {
        return false;
    }

    @Override
    public boolean deleteAccount(int id) {
        return false;
    }
}
