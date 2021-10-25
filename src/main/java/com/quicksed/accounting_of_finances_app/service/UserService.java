package com.quicksed.accounting_of_finances_app.service;

import com.quicksed.accounting_of_finances_app.dto.user.UserCreateDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserUpdateDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserWithRolesDto;
import com.quicksed.accounting_of_finances_app.dto.user.filter.UserFilterDto;
import com.quicksed.accounting_of_finances_app.entity.User;
import javassist.NotFoundException;

import java.util.Collection;
import java.util.List;

public interface UserService {

    UserDto createUser(UserCreateDto user);

    Integer getUserIdByEmail(String email);

    UserWithRolesDto getUserById(int id) throws NotFoundException;

    UserWithRolesDto getUserByEmail(String email) throws NotFoundException;

    void editRole(Integer userId, Collection<String> roleCodes) throws NotFoundException;

    List<UserWithRolesDto> getFilteredUsersList(Collection<UserFilterDto> filters);

    List<UserWithRolesDto> getAllUsers();

    UserDto updateUser(int id, UserUpdateDto user) throws NotFoundException;

    void deleteUser(int id) throws NotFoundException;
}
