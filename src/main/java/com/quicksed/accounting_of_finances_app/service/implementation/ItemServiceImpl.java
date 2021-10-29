package com.quicksed.accounting_of_finances_app.service.implementation;

import com.quicksed.accounting_of_finances_app.dto.item.ItemCreateDto;
import com.quicksed.accounting_of_finances_app.dto.item.ItemDto;
import com.quicksed.accounting_of_finances_app.dto.item.ItemUpdateDto;
import com.quicksed.accounting_of_finances_app.entity.Account;
import com.quicksed.accounting_of_finances_app.entity.Category;
import com.quicksed.accounting_of_finances_app.entity.Item;
import com.quicksed.accounting_of_finances_app.helper.OptionalChecker;
import com.quicksed.accounting_of_finances_app.repository.AccountRepository;
import com.quicksed.accounting_of_finances_app.repository.CategoryRepository;
import com.quicksed.accounting_of_finances_app.repository.ItemRepository;
import com.quicksed.accounting_of_finances_app.service.ItemService;
import com.quicksed.accounting_of_finances_app.service.factory.ItemFactory;
import com.quicksed.accounting_of_finances_app.service.mapper.ItemMapper;
import javassist.NotFoundException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

    private final ItemMapper itemMapper;
    private final ItemFactory itemFactory;

    public ItemServiceImpl(ItemRepository itemRepository, AccountRepository accountRepository,
                           CategoryRepository categoryRepository, ItemMapper itemMapper, ItemFactory itemFactory) {
        this.itemRepository = itemRepository;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
        this.itemMapper = itemMapper;
        this.itemFactory = itemFactory;
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public ItemDto createItem(ItemCreateDto itemCreateDto) throws NotFoundException {
        Optional<Account> accountOptional = accountRepository.findById(itemCreateDto.getAccountId());
        Optional<Category> categoryOptional = categoryRepository.findById(itemCreateDto.getCategoryId());

        Account account = OptionalChecker.checkOptional(accountOptional);
        Category category = OptionalChecker.checkOptional(categoryOptional);

        Item item = itemFactory.build(
                itemCreateDto.getName(),
                itemCreateDto.getDate(),
                itemCreateDto.getValue(),
                itemCreateDto.getComment(),
                account,
                category
        );

        item = itemRepository.save(item);
        return itemMapper.mapItemToItemDto(item);
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public ItemDto getItem(int id) throws NotFoundException {
        Optional<Item> itemOptional = itemRepository.findById(id);
        Item item = OptionalChecker.checkOptional(itemOptional);

        return itemMapper.mapItemToItemDto(item);
    }

    @Override
    public List<ItemDto> getUserItemsByEmail(String email) {
        List<Item> items = itemRepository.findUserItemsByEmail(email);
        return itemMapper.mapItemToItemDto(items);
    }

    @Transactional
    @Override
    public List<ItemDto> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return itemMapper.mapItemToItemDto(items);
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public ItemDto updateItem(int id, ItemUpdateDto itemUpdateDto) throws NotFoundException {
        Optional<Item> itemOptional = itemRepository.findById(id);
        Item item = OptionalChecker.checkOptional(itemOptional);

        item.setName(itemUpdateDto.getName());
        item.setDate(itemUpdateDto.getDate());
        item.setValue(itemUpdateDto.getValue());
        item.setComment(itemUpdateDto.getComment());

        itemRepository.saveAndFlush(item);
        return itemMapper.mapItemToItemDto(item);
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public void deleteItem(int id) throws NotFoundException {
        Optional<Item> itemOptional = itemRepository.findById(id);
        Item item = OptionalChecker.checkOptional(itemOptional);

        itemRepository.delete(item);
    }
}
