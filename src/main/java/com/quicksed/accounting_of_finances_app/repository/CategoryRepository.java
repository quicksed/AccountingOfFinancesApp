package com.quicksed.accounting_of_finances_app.repository;

import com.quicksed.accounting_of_finances_app.entity.Account;
import com.quicksed.accounting_of_finances_app.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByUserId(int userId);
}
