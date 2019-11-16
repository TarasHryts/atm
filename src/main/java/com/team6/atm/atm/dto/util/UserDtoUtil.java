package com.team6.atm.atm.dto.util;

import com.team6.atm.atm.dto.UserDto;
import com.team6.atm.atm.entity.User;

public class UserDtoUtil {
    public static User createUserFromDto(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
