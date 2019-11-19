package com.team6.atm.atm.dto.util;

import com.team6.atm.atm.dto.UserDto;
import com.team6.atm.atm.entity.User;
import org.apache.log4j.Logger;

public class UserDtoUtil {
    private static final Logger logger = Logger.getLogger(UserDtoUtil.class);

    public static User createUserFromDto(UserDto userDto) {
        logger.info("Create user from DTO");
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
