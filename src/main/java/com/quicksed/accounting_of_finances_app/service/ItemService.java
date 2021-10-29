package com.quicksed.accounting_of_finances_app.service;

import com.quicksed.accounting_of_finances_app.dto.item.ItemCreateDto;
import com.quicksed.accounting_of_finances_app.dto.item.ItemDto;
import com.quicksed.accounting_of_finances_app.dto.item.ItemUpdateDto;
import javassist.NotFoundException;

import java.util.List;

public interface ItemService {

    ItemDto createItem(ItemCreateDto item) throws NotFoundException;

    ItemDto getItem(int id) throws NotFoundException;

    List<ItemDto> getUserItemsByEmail(String email);

    List<ItemDto> getAllItems();

    ItemDto updateItem(int id, ItemUpdateDto item) throws NotFoundException;

    void deleteItem(int id) throws NotFoundException;
}
