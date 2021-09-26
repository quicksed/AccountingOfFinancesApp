package com.quicksed.accountingOfFinancesApp.service.implementations;

import com.quicksed.accountingOfFinancesApp.service.interfaces.IAccountService;
import com.quicksed.accountingOfFinancesApp.service.interfaces.ICategoryService;
import com.quicksed.accountingOfFinancesApp.service.interfaces.IItemService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Component
@Scope("prototype")
@Service
public class Item implements IItemService {

    private int id;
    private String name;
    private Date date;
    private Double value;
    private String comment;
    private IAccountService account;
    private ICategoryService category;

    public Item(IAccountService account, ICategoryService category ) {
        this.account = account;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
