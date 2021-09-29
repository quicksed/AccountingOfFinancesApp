package com.quicksed.accounting_of_finances_app.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

@Getter
public class UserCreateDto {

    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final Date birthDate;
    private final Date registrationDate;

    public UserCreateDto(@JsonProperty("name") String name,
                         @JsonProperty("surname") String surname,
                         @JsonProperty("email") String email,
                         @JsonProperty("password") String password,
                         @JsonProperty("birthDate") Date birthDate,
                         @JsonProperty("registrationDate") Date registrationDate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
    }
}
