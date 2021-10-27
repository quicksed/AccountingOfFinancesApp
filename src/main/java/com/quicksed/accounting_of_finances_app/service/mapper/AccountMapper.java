package com.quicksed.accounting_of_finances_app.service.mapper;

import com.quicksed.accounting_of_finances_app.dto.account.AccountDto;
import com.quicksed.accounting_of_finances_app.entity.Account;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {

    public AccountDto mapAccountToAccountDto(Account model) {
        return new AccountDto(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getUser().getId()
        );
    }

    public List<AccountDto> mapAccountToAccountDto(Collection<Account> model) {
        return model.stream()
                .map(this::mapAccountToAccountDto)
                .collect(Collectors.toList());
    }
}
