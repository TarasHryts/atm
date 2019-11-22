package com.team6.atm.atm.services.impl;

import com.team6.atm.atm.dto.UserDto;
import com.team6.atm.atm.entity.User;
import com.team6.atm.atm.repository.UserRepository;
import com.team6.atm.atm.services.UserService;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Optional<User> create(User user) {
        logger.info(this.getClass().getName() + " create user");
        User saveUser = userRepository.save(user);
        return Optional.ofNullable(saveUser);
    }

    @Transactional
    @Override
    public Optional<User> update(Long userId, UserDto userDto) {
        logger.info(this.getClass().getName() + " delete user id =" + userId);
        User newUser = userRepository.findById(userId).orElseThrow();
        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(userDto.getPassword());
        return Optional.of(newUser);
    }

    @Override
    public Optional<User> update(Long userId, User user) {
        user.setUserId(userId);
        userRepository.saveAndFlush(user);
        return Optional.of(user);
    }

    @Transactional
    @Override
    public void delete(User user) {
        logger.info(this.getClass().getName() + " delete user id =" + user.getUserId());
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> getUserById(Long userId) {
        logger.info(this.getClass().getName() + " delete user id =" + userId);
        return userRepository.findById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        logger.info(this.getClass().getName() + " show all users");
        return userRepository.findAll();
    }
}
