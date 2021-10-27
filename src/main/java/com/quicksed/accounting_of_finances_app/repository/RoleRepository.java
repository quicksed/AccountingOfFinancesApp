package com.quicksed.accounting_of_finances_app.repository;

import com.quicksed.accounting_of_finances_app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Set<Role> findAllByCodeIn(Collection<String> codes);
}
