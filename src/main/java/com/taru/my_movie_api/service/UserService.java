package com.taru.my_movie_api.service;

import com.taru.my_movie_api.dto.UserDTO;

public interface UserService {

    Boolean existsByUsername(String username);

    UserDTO createUser(UserDTO user);

    UserDTO updateUserById(int userId, UserDTO user);

    UserDTO getUserById(int userId);

    void deleteUserById(int userId);
}
