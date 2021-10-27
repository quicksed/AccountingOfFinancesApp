package com.quicksed.accounting_of_finances_app.controller;

import com.quicksed.accounting_of_finances_app.annotation.Loggable;
import com.quicksed.accounting_of_finances_app.dto.user.UserCreateDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserUpdateDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserWithRolesDto;
import com.quicksed.accounting_of_finances_app.dto.user.filter.UserFilterDto;
import com.quicksed.accounting_of_finances_app.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Loggable
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userById/{id}")
    public UserWithRolesDto getUserById(@PathVariable("id") int id) throws NotFoundException {
        return userService.getUserById(id);
    }

    @GetMapping("/userByEmail/{email}")
    public UserWithRolesDto getUserByEmail(@PathVariable("email") String email) throws NotFoundException {
        return userService.getUserByEmail(email);
    }

    @PostMapping
    public List<UserWithRolesDto> getUsers(@RequestBody Collection<UserFilterDto> filters) {
        return userService.getFilteredUsersList(filters);
    }

    @GetMapping("/getAllUsers")
    public List<UserWithRolesDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/{email}/roles")
    public void editRoles(@PathVariable String email,
                          @RequestBody Collection<String> newRoleCodes) throws NotFoundException {

        Integer userId = userService.getUserIdByEmail(email);
        userService.editRole(userId, newRoleCodes);
    }


    @PostMapping("/")
    public UserDto createUser(@RequestBody UserCreateDto userCreateDto) {
        return userService.createUser(userCreateDto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@RequestBody UserUpdateDto userUpdateDto,
                              @PathVariable("id") Integer userId) throws NotFoundException {

        return userService.updateUser(userId, userUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id) throws NotFoundException {
        userService.deleteUser(id);
    }
}
