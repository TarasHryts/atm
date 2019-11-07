package com.team6.atm.atm.services.impl;

import com.team6.atm.atm.entity.User;
import com.team6.atm.atm.repository.UserRepository;
import com.team6.atm.atm.services.UserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public Optional<User> create(User user) {
        User saveUser = userRepository.save(user);
        return Optional.ofNullable(saveUser);
    }
    @Transactional
    @Override
    public Optional<User> update(User user) {
        User newUser = userRepository.save(user);
        return Optional.of(newUser);
    }
    @Transactional
    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
    @Transactional(readOnly = true)
    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }
}
