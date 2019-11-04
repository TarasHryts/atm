package com.team6.atm.atm.services.impl;

import com.team6.atm.atm.entity.User;
import com.team6.atm.atm.services.UserService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public Optional<User> create(User user) {
        return Optional.empty();
    }

    @Override
    public Optional<User> update(User user) {
        return Optional.empty();
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return Optional.empty();
    }
}
