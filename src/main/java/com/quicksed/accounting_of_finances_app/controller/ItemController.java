package com.quicksed.accounting_of_finances_app.controller;

import com.quicksed.accounting_of_finances_app.annotation.Loggable;
import com.quicksed.accounting_of_finances_app.dto.item.ItemCreateDto;
import com.quicksed.accounting_of_finances_app.dto.item.ItemDto;
import com.quicksed.accounting_of_finances_app.dto.item.ItemUpdateDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserWithRolesDto;
import com.quicksed.accounting_of_finances_app.helper.RoleChecker;
import com.quicksed.accounting_of_finances_app.service.ItemService;
import com.quicksed.accounting_of_finances_app.service.UserService;
import javassist.NotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Loggable
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final UserService userService;

    public ItemController(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ItemDto getItem(@PathVariable("id") int id) throws NotFoundException {
        if (!RoleChecker.isAdminUser()){
            isAvailableItemToThisUser(id);
        }

        return itemService.getItem(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/getUserItemByEmail/{email}")
    public List<ItemDto> getUserItemByEmail(@PathVariable("email") String email) {
        if (!RoleChecker.isAdminUser()) {
            isAvailableItemsToThisUser(email);
        }

        return itemService.getUserItemsByEmail(email);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/")
    public List<ItemDto> getAllItems() {
        return itemService.getAllItems();
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/")
    public ItemDto createItem(@RequestBody ItemCreateDto createItemDto) throws NotFoundException {
        return itemService.createItem(createItemDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ItemDto updateItem(@RequestBody ItemUpdateDto itemUpdateDto,
                              @PathVariable("id") Integer itemId) throws NotFoundException {
        return itemService.updateItem(itemId, itemUpdateDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable("id") int id) throws NotFoundException {
        itemService.deleteItem(id);
    }

    private void isAvailableItemToThisUser(int itemId) {
        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        List<ItemDto> itemListByAuthenticatedUser = itemService.getUserItemsByEmail(authenticatedUserEmail);

        if (itemListByAuthenticatedUser.stream().noneMatch(item -> item.getId() == itemId)){
            throw new AccessDeniedException("Access denied!");
        }
    }

    private void isAvailableItemsToThisUser(String email) {
        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!authenticatedUserEmail.equals(email)){
            throw new AccessDeniedException("Access denied!");
        }
    }
}
