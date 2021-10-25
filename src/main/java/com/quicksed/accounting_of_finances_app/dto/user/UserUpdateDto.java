package com.quicksed.accounting_of_finances_app.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.Instant;

@Getter
public class UserUpdateDto {

    private final String name;
    private final String surname;
    private final String password;
    private final Instant birthDate;

    public UserUpdateDto(@JsonProperty("name") String name,
                         @JsonProperty("surname") String surname,
                         @JsonProperty("password") String password,
                         @JsonProperty("birthDate") Instant birthDate) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.birthDate = birthDate;
    }
}
