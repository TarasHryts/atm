package com.team6.atm.atm.controller;

import com.team6.atm.atm.dto.UserDto;
import com.team6.atm.atm.dto.util.UserDtoUtil;
import com.team6.atm.atm.entity.User;
import com.team6.atm.atm.exception.UserNotFoundException;
import com.team6.atm.atm.services.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public void add(@Valid @RequestBody UserDto userDto) {
        userService.create(UserDtoUtil.createUserFromDto(userDto));
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getById(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId).orElseThrow(
                () -> new UserNotFoundException("User with ID " + userId + " not found."));
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") Long userId) {
        userService.delete(getById(userId));
    }

    @PutMapping("/{userId}")
    public User update(@PathVariable("userId") Long userId, @RequestBody UserDto userDto) {
        return userService.update(userId, userDto).orElseThrow(
                () -> new UserNotFoundException("User with ID " + userId + " not found."));
    }
}
