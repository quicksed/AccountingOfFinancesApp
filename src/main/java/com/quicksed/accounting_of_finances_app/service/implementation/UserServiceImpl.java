package com.quicksed.accounting_of_finances_app.service.implementation;

import com.quicksed.accounting_of_finances_app.dto.user.UserCreateDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserUpdateDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserWithRolesDto;
import com.quicksed.accounting_of_finances_app.dto.user.filter.UserFilterDto;
import com.quicksed.accounting_of_finances_app.entity.Role;
import com.quicksed.accounting_of_finances_app.entity.User;
import com.quicksed.accounting_of_finances_app.repository.RoleRepository;
import com.quicksed.accounting_of_finances_app.repository.UserRepository;
import com.quicksed.accounting_of_finances_app.repository.specification.UserSpecification;
import com.quicksed.accounting_of_finances_app.service.UserService;
import com.quicksed.accounting_of_finances_app.service.factory.UserFactory;
import com.quicksed.accounting_of_finances_app.service.mapper.UserMapper;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final UserMapper userMapper;
    private final UserFactory userFactory;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           UserMapper userMapper, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.userFactory = userFactory;
    }

    @Transactional
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
    public Integer getUserIdByEmail(String email) {
        return userRepository.findUserByEmail(email).getId();
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public UserWithRolesDto getUserById(int id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException("User not found!");
        }

        return userMapper.mapUserToUserWithRolesDto(user.get());
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public UserWithRolesDto getUserByEmail(String email) throws NotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new NotFoundException("User not found!");
        }

        return userMapper.mapUserToUserWithRolesDto(user.get());
    }

    @Transactional
    @Override
    public void editRole(Integer userId, Collection<String> roleCodes) throws NotFoundException {
        Optional<User> user = userRepository.findById(userId);
        Set<Role> newRoles = roleRepository.findAllByCodeIn(roleCodes);

        if (user.isEmpty()) {
            throw new NotFoundException("User not found!");
        }

        user.get().setRoles(newRoles);
        userRepository.save(user.get());
    }

    @Override
    public List<UserWithRolesDto> getFilteredUsersList(Collection<UserFilterDto> filters) {
        List<User> users = userRepository.findAll(UserSpecification.findUsers(filters));
        return userMapper.mapUserToUserWithRolesDto(users);
    }

    @Transactional
    @Override
    public List<UserWithRolesDto> getAllUsers() {
        List<User> users = userRepository.findAllWithRoles();
        return userMapper.mapUserToUserWithRolesDto(users);
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public UserDto updateUser(int id, UserUpdateDto userUpdateDto) throws NotFoundException {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new NotFoundException("User not found!");
        }

        User user = userOptional.get();

        user.setName(userUpdateDto.getName());
        user.setSurname(userUpdateDto.getSurname());
        user.setPassword(userUpdateDto.getPassword());
        user.setBirthDate(userUpdateDto.getBirthDate());

        return userMapper.mapUserToUserDto(user);
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public void deleteUser(int id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException("User not found!");
        }

        userRepository.delete(user.get());
    }
}
