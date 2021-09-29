package com.quicksed.accounting_of_finances_app.service;

import com.quicksed.accounting_of_finances_app.dto.account.AccountCreateDto;
import com.quicksed.accounting_of_finances_app.dto.account.AccountDto;
import com.quicksed.accounting_of_finances_app.dto.account.AccountUpdateDto;
import com.quicksed.accounting_of_finances_app.entity.User;

import java.util.List;

public interface AccountService {

    void createAccount(AccountCreateDto account);

    AccountDto getAccountById(int id);

    List<AccountDto> getAllAccounts();

    List<AccountDto> getUsersAccounts(int userId);

    boolean updateAccount(int id, AccountUpdateDto account);

    boolean deleteAccount(int id);
}
