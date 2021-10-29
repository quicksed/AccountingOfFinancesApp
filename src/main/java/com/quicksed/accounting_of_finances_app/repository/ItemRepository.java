package com.quicksed.accounting_of_finances_app.repository;

import com.quicksed.accounting_of_finances_app.entity.Item;
import com.quicksed.accounting_of_finances_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query(value = "SELECT item FROM User user JOIN user.accounts account" +
            " JOIN account.items item WHERE user.email = :email")
    List<Item> findUserItemsByEmail(@Param("email") String email);
}
