package com.quicksed.accounting_of_finances_app.service.mapper;

import com.quicksed.accounting_of_finances_app.dto.user.UserDto;
import com.quicksed.accounting_of_finances_app.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto mapUserToUserDto(User model) {
        return new UserDto(
                model.getId(),
                model.getName(),
                model.getSurname(),
                model.getEmail(),
                model.getPassword(),
                model.getBirthDate(),
                model.getRegistrationDate()
        );
    }

    public List<UserDto> mapUserToUserDto(Collection<User> model) {
        return model.stream()
                .map(this::mapUserToUserDto)
                .collect(Collectors.toList());
    }
}
