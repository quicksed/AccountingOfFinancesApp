package com.quicksed.accounting_of_finances_app.service.mapper;

import com.quicksed.accounting_of_finances_app.dto.item.ItemDto;
import com.quicksed.accounting_of_finances_app.entity.Item;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {

    public ItemDto mapItemToItemDto(Item model) {
        return new ItemDto(
                model.getId(),
                model.getName(),
                model.getDate(),
                model.getValue(),
                model.getComment(),
                model.getAccount().getId(),
                model.getCategory().getId()
        );
    }

    public List<ItemDto> mapItemToItemDto(Collection<Item> model) {
        return model.stream()
                .map(this::mapItemToItemDto)
                .collect(Collectors.toList());
    }
}
