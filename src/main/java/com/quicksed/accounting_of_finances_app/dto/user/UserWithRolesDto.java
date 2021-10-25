package com.quicksed.accounting_of_finances_app.dto.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserWithRolesDto {

    @EqualsAndHashCode.Include
    private final int id;
    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final Instant birthDate;
    private final Instant registrationDate;

    private final List<String> codes;

    public UserWithRolesDto(Integer id,
                            String name, String surname, String email,
                            String password, Instant birthDate, Instant registrationDate, List<String> codes) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.registrationDate = registrationDate;
        this.codes = codes;
    }
}
