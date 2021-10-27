package com.quicksed.accounting_of_finances_app.service;

import com.quicksed.accounting_of_finances_app.dto.account.AccountCreateDto;
import com.quicksed.accounting_of_finances_app.dto.account.AccountDto;
import com.quicksed.accounting_of_finances_app.dto.account.AccountUpdateDto;
import javassist.NotFoundException;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountCreateDto account) throws NotFoundException;

    AccountDto getAccountById(int id) throws NotFoundException;

    List<AccountDto> getAllAccounts();

    List<AccountDto> getUsersAccounts(int userId);

    AccountDto updateAccount(int id, AccountUpdateDto account) throws NotFoundException;

    void deleteAccount(int id) throws NotFoundException;
}
