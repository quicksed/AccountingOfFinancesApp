package com.quicksed.accountingOfFinancesApp;

import com.quicksed.accountingOfFinancesApp.service.interfaces.IAccountService;
import com.quicksed.accountingOfFinancesApp.service.interfaces.ICategoryService;
import com.quicksed.accountingOfFinancesApp.service.interfaces.IUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.quicksed.accountingOfFinancesApp.service.implementations.*;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(App.class);

        IUserService user = applicationContext.getBean("user", IUserService.class);
        user.setName("Makar");
        user.setSurname("Aleksandrov");
        System.out.println("User name: " + user.getUserNameAndSurname());

        IAccountService account = applicationContext.getBean("account", IAccountService.class);
        System.out.println("User name from account that created by user: " + account.getUser().getUserNameAndSurname());

        ICategoryService category = applicationContext.getBean("category", ICategoryService.class);
        System.out.println("User name from category that created by user: "  + category.getUser().getUserNameAndSurname());
    }
}
