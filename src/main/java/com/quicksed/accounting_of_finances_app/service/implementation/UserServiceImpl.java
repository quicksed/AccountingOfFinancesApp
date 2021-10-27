package com.quicksed.accounting_of_finances_app.service.implementation;

import com.quicksed.accounting_of_finances_app.dto.authentication.UserAuthenticationInfoDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserCreateDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserUpdateDto;
import com.quicksed.accounting_of_finances_app.dto.user.UserWithRolesDto;
import com.quicksed.accounting_of_finances_app.dto.user.filter.UserFilterDto;
import com.quicksed.accounting_of_finances_app.entity.Role;
import com.quicksed.accounting_of_finances_app.entity.User;
import com.quicksed.accounting_of_finances_app.helper.OptionalChecker;
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
import java.util.stream.Collectors;

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
        Optional<User> userOptional = userRepository.findById(id);
        User user = OptionalChecker.checkOptional(userOptional);

        return userMapper.mapUserToUserWithRolesDto(user);
    }

    @Retryable(NotFoundException.class)
    @Transactional
    @Override
    public UserWithRolesDto getUserByEmail(String email) throws NotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = OptionalChecker.checkOptional(userOptional);

        return userMapper.mapUserToUserWithRolesDto(user);
    }

    @Transactional
    @Override
    public void editRole(Integer userId, Collection<String> roleCodes) throws NotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = OptionalChecker.checkOptional(userOptional);

        Set<Role> newRoles = roleRepository.findAllByCodeIn(roleCodes);

        user.setRoles(newRoles);
        userRepository.save(user);
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
        User user = OptionalChecker.checkOptional(userOptional);

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
        Optional<User> userOptional = userRepository.findById(id);
        User user = OptionalChecker.checkOptional(userOptional);

        userRepository.delete(user);
    }

    @Override
    public Optional<UserAuthenticationInfoDto> findAuthenticationInfo(String email) {
        Optional<User> userOptional = userRepository.findUserWithRolesByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return Optional.of(new UserAuthenticationInfoDto(
                    user.getId(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles().stream().map(Role::getCode).collect(Collectors.toSet())
            ));
        } else {
            return Optional.empty();
        }
    }
}
