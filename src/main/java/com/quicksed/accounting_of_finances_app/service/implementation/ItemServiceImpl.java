package com.quicksed.accounting_of_finances_app.service.implementation;

import com.quicksed.accounting_of_finances_app.dto.item.ItemCreateDto;
import com.quicksed.accounting_of_finances_app.dto.item.ItemDto;
import com.quicksed.accounting_of_finances_app.dto.item.ItemUpdateDto;
import com.quicksed.accounting_of_finances_app.entity.Account;
import com.quicksed.accounting_of_finances_app.entity.Item;
import com.quicksed.accounting_of_finances_app.repository.AccountRepository;
import com.quicksed.accounting_of_finances_app.repository.ItemRepository;
import com.quicksed.accounting_of_finances_app.service.ItemService;
import com.quicksed.accounting_of_finances_app.service.factory.AccountFactory;
import com.quicksed.accounting_of_finances_app.service.factory.ItemFactory;
import com.quicksed.accounting_of_finances_app.service.mapper.AccountMapper;
import com.quicksed.accounting_of_finances_app.service.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final ItemFactory itemFactory;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper, ItemFactory itemFactory) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.itemFactory = itemFactory;
    }

    @Override
    public ItemDto createItem(ItemCreateDto itemCreateDto) {

        Item item = itemFactory.build(
                itemCreateDto.getName(),
                itemCreateDto.getDate(),
                itemCreateDto.getValue(),
                itemCreateDto.getComment(),
                itemCreateDto.getAccountId(),
                itemCreateDto.getCategoryId()
        );

        item = itemRepository.saveAndFlush(item);
        return itemMapper.mapItemToItemDto(item);
    }

    @Override
    public ItemDto getItem(int id) {
        Item item = itemRepository.findById(id).orElseThrow();
        return itemMapper.mapItemToItemDto(item);
    }

    @Override
    public List<ItemDto> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return itemMapper.mapItemToItemDto(items);
    }

    @Override
    public ItemDto updateItem(int id, ItemUpdateDto itemUpdateDto) {
        Item item = itemRepository.findById(id).orElseThrow();

        item.setName(itemUpdateDto.getName());
        item.setDate(itemUpdateDto.getDate());
        item.setValue(itemUpdateDto.getValue());
        item.setComment(itemUpdateDto.getComment());

        itemRepository.saveAndFlush(item);
        return itemMapper.mapItemToItemDto(item);
    }

    @Override
    public void deleteItem(int id) {
        Item item = itemRepository.findById(id).orElseThrow();

        itemRepository.delete(item);
    }
}
