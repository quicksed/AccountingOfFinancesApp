package com.quicksed.accounting_of_finances_app.dto.item;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.Instant;
import java.util.Date;

@Getter
public class ItemCreateDto {

    private final String name;
    private final Instant date;
    private final Double value;
    private final String comment;
    private final Integer accountId;
    private final Integer categoryId;

    public ItemCreateDto(@JsonProperty("name") String name,
                         @JsonProperty("date") Instant date,
                         @JsonProperty("value") Double value,
                         @JsonProperty("comment") String comment,
                         @JsonProperty("accountId") Integer accountId,
                         @JsonProperty("categoryId") Integer categoryId) {
        this.name = name;
        this.date = date;
        this.value = value;
        this.comment = comment;
        this.accountId = accountId;
        this.categoryId = categoryId;
    }
}
