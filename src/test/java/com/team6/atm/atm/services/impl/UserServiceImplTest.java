package com.team6.atm.atm.services.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.team6.atm.atm.dto.UserDto;
import com.team6.atm.atm.entity.User;
import com.team6.atm.atm.repository.UserRepository;
import com.team6.atm.atm.services.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class UserServiceImplTest {
    private UserService userService;
    private UserRepository userRepository;
    private User user;
    private List<User> userList;

    @Before
    public void setUp() throws Exception {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
        user = new User();
        user.setUsername("Petro");
        user.setEmail("petro.bamber@gmail.com");
        user.setPassword("Gtnhj<fvgthyjvth1!");
        user.setUserId(1L);
        userList = new ArrayList<>();
        userList.add(user);
    }

    @Test
    public void createOk() {
        when(userRepository.save(user)).thenReturn(user);
        Assert.assertEquals(user, userService.create(user).get());
        Assert.assertEquals(user.getUsername(), userService.create(user).get().getUsername());
        Assert.assertEquals(user.getEmail(), userService.create(user).get().getEmail());

    }

    @Test
    public void deleteOk() {
        Integer sizeBefore = userRepository.findAll().size();
        userService.delete(user);
        Integer sizeAfter = userRepository.findAll().size();
        Assert.assertEquals(sizeBefore, sizeAfter);
    }

    @Test
    public void getUserByIdOk() {
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        Assert.assertEquals(user, userService.getUserById(1L).get());
        Assert.assertEquals(user.getUsername(), userService.getUserById(1L).get().getUsername());
    }

    @Test
    public void getAllUsersOk() {
        when(userRepository.findAll()).thenReturn(userList);
        List<User> allUsers = userService.getAllUsers();
        Assert.assertEquals(userList.size(), allUsers.size());
        Assert.assertEquals(userList, allUsers);
    }
}