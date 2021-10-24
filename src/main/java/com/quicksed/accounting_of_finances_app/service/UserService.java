package com.quicksed.accounting_of_finances_app.service;

import com.quicksed.accounting_of_finances_app.dto.user.UserCreateDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserUpdateDto;
import com.quicksed.accounting_of_finances_app.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserCreateDto user);

    UserDto getUserById(int id);

    UserDto getUserByEmail(String email);

    List<UserDto> getAllUsers();

    UserDto updateUser(int id, UserUpdateDto user);

    void deleteUser(int id);
}
