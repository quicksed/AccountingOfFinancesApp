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

    @GetMapping("/getUsersItems/user/{userId}")
    public List<ItemDto> getUsersItems(int userId) {
        return itemService.getUsersItems(userId);
    }

    @GetMapping("/getAllItems")
    public List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping("/createItem")
    public void createItem(@RequestBody ItemDto createItemDto) {

        itemService.createItem(new ItemCreateDto(
                createItemDto.getName(),
                createItemDto.getDate(),
                createItemDto.getValue(),
                createItemDto.getComment(),
                createItemDto.getAccount(),
                createItemDto.getCategory()
        ));
    }

    @PutMapping("/item/{id}")
    public void updateItem(@RequestBody ItemUpdateDto itemUpdateDto,
                               @PathVariable("id") Integer itemId) {

        itemService.updateItem(itemId, itemUpdateDto);
    }

    @DeleteMapping("/delete/item/id}")
    public boolean deleteAccount(int id) {
        return itemService.deleteItem(id);
    }
}
