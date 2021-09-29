package com.quicksed.accounting_of_finances_app.controller;

import com.quicksed.accounting_of_finances_app.dto.account.AccountCreateDto;
import com.quicksed.accounting_of_finances_app.dto.account.AccountDto;
import com.quicksed.accounting_of_finances_app.dto.account.AccountUpdateDto;
import com.quicksed.accounting_of_finances_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/getAccount-{id}")
    public AccountDto getAccount(int id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/getUsersAccounts/user-{userId}")
    public List<AccountDto> getUsersAccounts(int userId) {
        return accountService.getUsersAccounts(userId);
    }

    @GetMapping("/getAllAccounts")
    public List<AccountDto> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping("/createAccount")
    public void createAccount(@RequestBody AccountCreateDto accountCreateDto) {

        accountService.createAccount(new AccountCreateDto(
                accountCreateDto.getName(),
                accountCreateDto.getDescription(),
                accountCreateDto.getUser()
        ));
    }

    @PutMapping("/account-{id}")
    public void updateAccount(@RequestBody AccountUpdateDto accountUpdateDto,
                              @PathVariable("id") Integer accountId) {

        accountService.updateAccount(accountId, accountUpdateDto);
    }

    @DeleteMapping("/delete/account-{id}")
    public boolean deleteAccount(int id) {
        return accountService.deleteAccount(id);
    }
}
