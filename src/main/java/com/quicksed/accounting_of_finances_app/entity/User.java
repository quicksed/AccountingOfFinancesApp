package com.quicksed.accounting_of_finances_app.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Date birthDate;
    private Date registrationDate;
}
