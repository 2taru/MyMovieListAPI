package com.taru.my_movie_api.mapper;

import com.taru.my_movie_api.dto.UserDTO;
import com.taru.my_movie_api.models.User;

public class UserMapper {

    public static UserDTO mapToDto(User user) {

        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .build();
    }

    public static User mapToEntity(UserDTO userDTO) {

        return User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .username(userDTO.getUsername())
                .build();
    }
}
