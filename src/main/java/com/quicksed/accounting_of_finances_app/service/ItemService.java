package com.quicksed.accounting_of_finances_app.service;

import com.quicksed.accounting_of_finances_app.dto.item.ItemCreateDto;
import com.quicksed.accounting_of_finances_app.dto.item.ItemDto;
import com.quicksed.accounting_of_finances_app.dto.item.ItemUpdateDto;

import java.util.List;

public interface ItemService {

    ItemDto createItem(ItemCreateDto item);

    ItemDto getItem(int id);

    List<ItemDto> getAllItems();

    ItemDto updateItem(int id, ItemUpdateDto item);

    void deleteItem(int id);
}
