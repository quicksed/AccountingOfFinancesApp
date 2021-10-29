package com.quicksed.accounting_of_finances_app.controller;

import com.quicksed.accounting_of_finances_app.annotation.Loggable;
import com.quicksed.accounting_of_finances_app.dto.account.AccountCreateDto;
import com.quicksed.accounting_of_finances_app.dto.account.AccountDto;
import com.quicksed.accounting_of_finances_app.dto.account.AccountUpdateDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserWithRolesDto;
import com.quicksed.accounting_of_finances_app.helper.RoleChecker;
import com.quicksed.accounting_of_finances_app.service.AccountService;
import com.quicksed.accounting_of_finances_app.service.UserService;
import javassist.NotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Loggable
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final UserService userService;

    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    public AccountDto getAccount(@PathVariable("id") int id) throws NotFoundException, AccessDeniedException {
        if (!RoleChecker.isAdminUser()) {
            checkUserAccessToAccountById(id);
        }

        return accountService.getAccountById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/getAccountsByUserId/{userId}")
    public List<AccountDto> getUsersAccounts(@PathVariable("userId") int userId) throws NotFoundException {
        if (!RoleChecker.isAdminUser()) {
            checkUserAccessToAccountsByUserId(userId);
        }

        return accountService.getUsersAccounts(userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/")
    public List<AccountDto> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/")
    public AccountDto createAccount(@RequestBody AccountCreateDto accountCreateDto) throws NotFoundException {
        return accountService.createAccount(accountCreateDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping("/{id}")
    public AccountDto updateAccount(@RequestBody AccountUpdateDto accountUpdateDto,
                                    @PathVariable("id") Integer id) throws NotFoundException, AccessDeniedException {
        if (!RoleChecker.isAdminUser()) {
            checkUserAccessToAccountById(id);
        }

        return accountService.updateAccount(id, accountUpdateDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable("id") int id) throws NotFoundException {
        if (!RoleChecker.isAdminUser()) {
            checkUserAccessToAccountById(id);
        }

        accountService.deleteAccount(id);
    }

    private void checkUserAccessToAccountById(int accountId) throws NotFoundException {
        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        UserWithRolesDto user = userService.getUserByEmail(authenticatedUserEmail);
        AccountDto account = accountService.getAccountById(accountId);

        if (account.getUserId() != user.getId()){
            throw new AccessDeniedException("Access denied!");
        }
    }

    private void checkUserAccessToAccountsByUserId(int userId) throws NotFoundException {
        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserWithRolesDto user = userService.getUserById(userId);

        if (!authenticatedUserEmail.equals(user.getEmail())){
            throw new AccessDeniedException("Access denied!");
        }
    }
}
