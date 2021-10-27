package com.quicksed.accounting_of_finances_app.service.mapper;

import com.quicksed.accounting_of_finances_app.dto.currency.CurrencyDto;
import com.quicksed.accounting_of_finances_app.entity.Currency;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CurrencyMapper {

    public CurrencyDto mapCurrencyToCurrencyDto(Currency model) {
        return new CurrencyDto(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getUser().getId()
        );
    }

    public List<CurrencyDto> mapCurrencyToCurrencyDto(Collection<Currency> model) {
        return model.stream()
                .map(this::mapCurrencyToCurrencyDto)
                .collect(Collectors.toList());
    }
}
