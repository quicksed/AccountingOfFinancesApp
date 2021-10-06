package com.quicksed.accounting_of_finances_app.repository;

import com.quicksed.accounting_of_finances_app.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

}
