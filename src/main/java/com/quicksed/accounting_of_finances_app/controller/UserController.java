package com.quicksed.accounting_of_finances_app.controller;

import com.quicksed.accounting_of_finances_app.dto.user.UserCreateDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserUpdateDto;
import com.quicksed.accounting_of_finances_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userById/user/{id}")
    public UserDto getUserById(int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/getAllUsers")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/createUser")
    public UserDto createUser(@RequestBody UserCreateDto userCreateDto) {
        return userService.createUser(userCreateDto);
    }

    @PutMapping("/user/{id}")
    public UserDto updateUser(@RequestBody UserUpdateDto userUpdateDto,
                              @PathVariable("id") Integer userId) {

        return userService.updateUser(userId, userUpdateDto);
    }

    @DeleteMapping("/delete/user/{id}")
    public void deleteUser(int id) {
        userService.deleteUser(id);
    }
}
