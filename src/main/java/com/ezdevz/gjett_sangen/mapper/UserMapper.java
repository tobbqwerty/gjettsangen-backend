package com.ezdevz.gjett_sangen.mapper;

import com.ezdevz.gjett_sangen.dto.UserDto;
import com.ezdevz.gjett_sangen.model.User;

public class UserMapper {

    public static UserDto toDTO(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
