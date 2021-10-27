package com.quicksed.accounting_of_finances_app.controller;

import com.quicksed.accounting_of_finances_app.annotation.Loggable;
import com.quicksed.accounting_of_finances_app.dto.item.ItemCreateDto;
import com.quicksed.accounting_of_finances_app.dto.item.ItemDto;
import com.quicksed.accounting_of_finances_app.dto.item.ItemUpdateDto;
import com.quicksed.accounting_of_finances_app.service.ItemService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Loggable
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    public ItemDto getItem(@PathVariable("id") int id) throws NotFoundException {
        return itemService.getItem(id);
    }

    @GetMapping("/")
    public List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping("/")
    public ItemDto createItem(@RequestBody ItemCreateDto createItemDto) throws NotFoundException {
        return itemService.createItem(createItemDto);
    }

    @PutMapping("/{id}")
    public ItemDto updateItem(@RequestBody ItemUpdateDto itemUpdateDto,
                              @PathVariable("id") Integer itemId) throws NotFoundException {

        return itemService.updateItem(itemId, itemUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable("id") int id) throws NotFoundException {
        itemService.deleteItem(id);
    }
}
