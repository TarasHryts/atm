package com.team6.atm.atm.dto;

import com.team6.atm.atm.entity.User;

public class UserDtoUtil {
    public static User getUserFromDto(UserDto userDto) {
        User user = new User();
        user.setUsername((userDto.getUsername()));
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
