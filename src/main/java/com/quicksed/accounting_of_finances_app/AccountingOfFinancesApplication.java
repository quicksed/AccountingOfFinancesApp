package com.quicksed.accounting_of_finances_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
public class AccountingOfFinancesApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(AccountingOfFinancesApplication.class);
    }
}
