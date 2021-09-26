package com.quicksed.accountingOfFinancesApp.service.implementations;

import com.quicksed.accountingOfFinancesApp.service.interfaces.IUserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Component
@Scope("singleton")
@Service
public class User implements IUserService {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Date birthDate;
    private Date registrationDate;

    @Override
    public String getUserNameAndSurname() {
        return String.format("%s %s", name, surname);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
