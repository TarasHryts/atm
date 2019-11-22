package com.team6.atm.atm.controller;

import com.team6.atm.atm.dto.UserDto;
import com.team6.atm.atm.dto.util.UserDtoUtil;
import com.team6.atm.atm.entity.User;
import com.team6.atm.atm.exception.UserNotFoundException;
import com.team6.atm.atm.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "accountId", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Successfully retrieved list"),
            @ApiResponse(code = 401,
                    message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403,
                    message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404,
                    message = "The resource you were trying to reach is not found")
    })
    public void add(@Valid @RequestBody UserDto userDto) {
        userService.create(UserDtoUtil.createUserFromDto(userDto));
    }

    @GetMapping("/all")
    @ApiOperation(value = "all", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Successfully retrieved list"),
            @ApiResponse(code = 401,
                    message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403,
                    message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404,
                    message = "The resource you were trying to reach is not found")
    })
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "userId", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Successfully retrieved list"),
            @ApiResponse(code = 401,
                    message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403,
                    message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404,
                    message = "The resource you were trying to reach is not found")
    })
    public User getById(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId).orElseThrow(
                () -> new UserNotFoundException("User with ID " + userId + " not found."));
    }

    @DeleteMapping("/{userId}")
    @ApiOperation(value = "userId", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Successfully retrieved list"),
            @ApiResponse(code = 401,
                    message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403,
                    message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404,
                    message = "The resource you were trying to reach is not found")
    })
    public void delete(@PathVariable("userId") Long userId) {
        userService.delete(getById(userId));
    }

    @PutMapping("/{userId}")
    @ApiOperation(value = "userId", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Successfully retrieved list"),
            @ApiResponse(code = 401,
                    message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403,
                    message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404,
                    message = "The resource you were trying to reach is not found")
    })
    public User update(@PathVariable("userId") Long userId, @RequestBody UserDto userDto) {
        return userService.update(userId, userDto).orElseThrow(
                () -> new UserNotFoundException("User with ID " + userId + " not found."));
    }
}
