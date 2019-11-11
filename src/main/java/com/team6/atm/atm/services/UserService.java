package com.team6.atm.atm.services;

import com.team6.atm.atm.dto.UserDto;
import com.team6.atm.atm.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> create(User user);

    Optional<User> update(Long userId, UserDto userDto);

    void delete(User user);

    Optional<User> getUserById(Long userId);

    List<User> getAllUsers();

}
