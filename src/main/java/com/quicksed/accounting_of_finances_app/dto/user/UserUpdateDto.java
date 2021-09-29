package com.quicksed.accounting_of_finances_app.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

@Getter
public class UserUpdateDto {

    private final String name;
    private final String surname;
    private final String password;
    private final Date birthDate;

    public UserUpdateDto(@JsonProperty("name") String name,
                         @JsonProperty("surname") String surname,
                         @JsonProperty("password") String password,
                         @JsonProperty("birthDate") Date birthDate) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.birthDate = birthDate;
    }
}
