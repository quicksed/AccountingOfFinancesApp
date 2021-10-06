package com.quicksed.accounting_of_finances_app.repository;

import com.quicksed.accounting_of_finances_app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByUserId(int userId);
}
