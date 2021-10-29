package com.quicksed.accounting_of_finances_app.dto.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.Instant;
import java.util.Date;

@Getter
public class ItemUpdateDto {

    private final String name;
    private final Instant date;
    private final Double value;
    private final String comment;

    public ItemUpdateDto(@JsonProperty("name") String name,
                         @JsonProperty("date") Instant date,
                         @JsonProperty("value") Double value,
                         @JsonProperty("comment") String comment) {
        this.name = name;
        this.date = date;
        this.value = value;
        this.comment = comment;
    }
}
