package com.team6.atm.atm.services;

import com.team6.atm.atm.entity.User;
import java.util.Optional;

public interface UserService {
    Optional<User> create(User user);
    Optional<User> update(User user);
    void delete(User user);
    Optional<User> getUserById(Long userId);

}
