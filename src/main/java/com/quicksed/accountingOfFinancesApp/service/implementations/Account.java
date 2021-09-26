package com.quicksed.accountingOfFinancesApp.service.implementations;

import com.quicksed.accountingOfFinancesApp.service.interfaces.IAccountService;
import com.quicksed.accountingOfFinancesApp.service.interfaces.IUserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Scope("prototype")
@Service
public class Account implements IAccountService {

    private int id;
    private String name;
    private String description;
    private IUserService user;

    public Account(IUserService user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUser(IUserService user) {
        this.user = user;
    }

    @Override
    public IUserService getUser() {
        return user;
    }
}
