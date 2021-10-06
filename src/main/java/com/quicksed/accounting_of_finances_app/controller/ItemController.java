package com.quicksed.accounting_of_finances_app.controller;

import com.quicksed.accounting_of_finances_app.dto.item.ItemCreateDto;
import com.quicksed.accounting_of_finances_app.dto.item.ItemDto;
import com.quicksed.accounting_of_finances_app.dto.item.ItemUpdateDto;
import com.quicksed.accounting_of_finances_app.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/getItem/{id}")
    public ItemDto getItem(int id) {
        return itemService.getItem(id);
    }

    @GetMapping("/getAllItems")
    public List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping("/createItem")
    public ItemDto createItem(@RequestBody ItemCreateDto createItemDto) {
        return itemService.createItem(createItemDto);
    }

    @PutMapping("/item/{id}")
    public ItemDto updateItem(@RequestBody ItemUpdateDto itemUpdateDto,
                              @PathVariable("id") Integer itemId) {

        return itemService.updateItem(itemId, itemUpdateDto);
    }

    @DeleteMapping("/delete/item/id}")
    public void deleteAccount(int id) {
        itemService.deleteItem(id);
    }
}
