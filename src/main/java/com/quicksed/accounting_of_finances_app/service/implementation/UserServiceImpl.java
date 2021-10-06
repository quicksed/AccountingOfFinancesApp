package com.quicksed.accounting_of_finances_app.service.implementation;

import com.quicksed.accounting_of_finances_app.dto.user.UserCreateDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserUpdateDto;
import com.quicksed.accounting_of_finances_app.entity.User;
import com.quicksed.accounting_of_finances_app.repository.UserRepository;
import com.quicksed.accounting_of_finances_app.service.UserService;
import com.quicksed.accounting_of_finances_app.service.factory.UserFactory;
import com.quicksed.accounting_of_finances_app.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserFactory userFactory;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userFactory = userFactory;
    }

    @Override
    public UserDto createUser(UserCreateDto userCreateDto) {
        User user = userFactory.build(
                userCreateDto.getName(),
                userCreateDto.getSurname(),
                userCreateDto.getEmail(),
                userCreateDto.getPassword(),
                userCreateDto.getBirthDate()
        );

        user = userRepository.saveAndFlush(user);
        return userMapper.mapUserToUserDto(user);
    }

    @Override
    public UserDto getUserById(int id) {
        User user = userRepository.findById(id).orElseThrow();
        return userMapper.mapUserToUserDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return userMapper.mapUserToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.mapUserToUserDto(users);
    }

    @Override
    public UserDto updateUser(int id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(id).orElseThrow();

        user.setName(userUpdateDto.getName());
        user.setSurname(userUpdateDto.getSurname());
        user.setPassword(userUpdateDto.getPassword());
        user.setBirthDate(userUpdateDto.getBirthDate());

        return userMapper.mapUserToUserDto(user);
    }

    @Override
    public void deleteUser(int id) {
        User user = userRepository.findById(id).orElseThrow();

        userRepository.delete(user);
    }
}
