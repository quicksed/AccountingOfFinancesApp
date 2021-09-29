package com.quicksed.accounting_of_finances_app.service;

import com.quicksed.accounting_of_finances_app.dto.user.UserCreateDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserUpdateDto;

import java.util.List;

public interface UserService {

    void createUser(UserCreateDto user);

    UserDto getUserById(int id);

    UserDto getUserByEmail(String email);

    List<UserDto> getAllUsers();

    boolean updateUser(int id, UserUpdateDto user);

    boolean deleteUser(int id);
}
