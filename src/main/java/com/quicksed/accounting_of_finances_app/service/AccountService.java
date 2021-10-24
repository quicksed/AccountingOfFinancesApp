package com.quicksed.accounting_of_finances_app.service;

import com.quicksed.accounting_of_finances_app.dto.account.AccountCreateDto;
import com.quicksed.accounting_of_finances_app.dto.account.AccountDto;
import com.quicksed.accounting_of_finances_app.dto.account.AccountUpdateDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountCreateDto account);

    AccountDto getAccountById(int id);

    List<AccountDto> getAllAccounts();

    List<AccountDto> getUsersAccounts(int userId);

    AccountDto updateAccount(int id, AccountUpdateDto account);

    void deleteAccount(int id);
}
