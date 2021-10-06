package com.quicksed.accounting_of_finances_app.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AccountUpdateDto {

    private final String name;
    private final String description;

    public AccountUpdateDto(@JsonProperty("name") String name,
                            @JsonProperty("description") String description) {
        this.name = name;
        this.description = description;
    }
}
