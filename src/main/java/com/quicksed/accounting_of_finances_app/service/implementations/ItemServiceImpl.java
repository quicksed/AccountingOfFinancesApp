package com.quicksed.accounting_of_finances_app.service.implementations;

import com.quicksed.accounting_of_finances_app.service.ItemService;
import com.quicksed.accounting_of_finances_app.dto.item.ItemCreateDto;
import com.quicksed.accounting_of_finances_app.dto.item.ItemDto;
import com.quicksed.accounting_of_finances_app.dto.item.ItemUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public void createItem(ItemCreateDto item) {

    }

    @Override
    public ItemDto getItem(int id) {
        return null;
    }

    @Override
    public List<ItemDto> getUsersItems(int userId) {
        return null;
    }

    @Override
    public List<ItemDto> getAllItems() {
        return null;
    }

    @Override
    public boolean updateItem(int id, ItemUpdateDto item) {
        return false;
    }

    @Override
    public boolean deleteItem(int id) {
        return false;
    }
}
