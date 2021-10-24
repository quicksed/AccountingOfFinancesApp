package com.quicksed.accounting_of_finances_app.service.factory;

import com.quicksed.accounting_of_finances_app.entity.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserFactory {

    public User build(String name, String surname, String email, String password, Date birthDate) {
        return new User(name, surname, email, password, birthDate);
    }
}
