package com.quicksed.accounting_of_finances_app.controller;

import com.quicksed.accounting_of_finances_app.annotation.Loggable;
import com.quicksed.accounting_of_finances_app.dto.account.AccountCreateDto;
import com.quicksed.accounting_of_finances_app.dto.account.AccountDto;
import com.quicksed.accounting_of_finances_app.dto.account.AccountUpdateDto;
import com.quicksed.accounting_of_finances_app.service.AccountService;
import javassist.NotFoundException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Loggable
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public AccountDto getAccount(@PathVariable("id") int id) throws NotFoundException {
        return accountService.getAccountById(id);
    }

    @GetMapping("/getAccountsByUserId/{userId}")
    public List<AccountDto> getUsersAccounts(@PathVariable("userId") int userId) {
        return accountService.getUsersAccounts(userId);
    }

    @GetMapping("/")
    public List<AccountDto> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping("/")
    public AccountDto createAccount(@RequestBody AccountCreateDto accountCreateDto) throws NotFoundException {
        return accountService.createAccount(accountCreateDto);
    }

    @PutMapping("/{id}")
    public AccountDto updateAccount(@RequestBody AccountUpdateDto accountUpdateDto,
                                    @PathVariable("id") Integer accountId) throws NotFoundException {
        return accountService.updateAccount(accountId, accountUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable("id") int id) throws NotFoundException {
        accountService.deleteAccount(id);
    }
}
