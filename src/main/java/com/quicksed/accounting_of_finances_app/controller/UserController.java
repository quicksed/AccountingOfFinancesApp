package com.quicksed.accounting_of_finances_app.controller;

import com.quicksed.accounting_of_finances_app.annotation.Loggable;
import com.quicksed.accounting_of_finances_app.dto.user.UserCreateDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserUpdateDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserWithRolesDto;
import com.quicksed.accounting_of_finances_app.dto.user.filter.UserFilterDto;
import com.quicksed.accounting_of_finances_app.helper.RoleChecker;
import com.quicksed.accounting_of_finances_app.service.UserService;
import javassist.NotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Loggable
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/userById/{id}")
    public UserWithRolesDto getUserById(@PathVariable("id") int id) throws NotFoundException {
        if (!RoleChecker.isAdminUser()) {
            UserWithRolesDto user = userService.getUserById(id);
            isAvailableUserToThisUser(user.getEmail());
        }

        return userService.getUserById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/userByEmail/{email}")
    public UserWithRolesDto getUserByEmail(@PathVariable("email") String email) throws NotFoundException {
        if (!RoleChecker.isAdminUser()) {
            isAvailableUserToThisUser(email);
        }

        return userService.getUserByEmail(email);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public List<UserWithRolesDto> getUsers(@RequestBody Collection<UserFilterDto> filters) {
        return userService.getFilteredUsersList(filters);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllUsers")
    public List<UserWithRolesDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{email}/roles")
    public void editRoles(@PathVariable String email,
                          @RequestBody Collection<String> newRoleCodes) throws NotFoundException {
        Integer userId = userService.getUserIdByEmail(email);
        userService.editRole(userId, newRoleCodes);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    public UserDto createUser(@RequestBody UserCreateDto userCreateDto) {
        return userService.createUser(userCreateDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping("/{id}")
    public UserDto updateUser(@RequestBody UserUpdateDto userUpdateDto,
                              @PathVariable("id") Integer userId) throws NotFoundException {
        if (!RoleChecker.isAdminUser()) {
            UserWithRolesDto user = userService.getUserById(userId);
            isAvailableUserToThisUser(user.getEmail());
        }

        return userService.updateUser(userId, userUpdateDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id) throws NotFoundException {
        userService.deleteUser(id);
    }

    private void isAvailableUserToThisUser(String email) {
        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!authenticatedUserEmail.equals(email)) {
            throw new AccessDeniedException("Access denied!");
        }
    }
}
