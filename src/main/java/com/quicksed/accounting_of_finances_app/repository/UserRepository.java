package com.quicksed.accounting_of_finances_app.repository;

import com.quicksed.accounting_of_finances_app.dto.user.UserDto;
import com.quicksed.accounting_of_finances_app.entity.User;
import com.quicksed.accounting_of_finances_app.entity.projection.UserIdProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends
        JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    Optional<User> findByEmail(String email);

    UserIdProjection findUserByEmail(String email);

    @EntityGraph("User.roles")
    Optional<User> findUserWithRolesByEmail(String email);

    @EntityGraph("User.roles")
    @Query("select u from User u")
    List<User> findAllWithRoles();
}
