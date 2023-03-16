package com.taru.my_movie_api.service;

import com.taru.my_movie_api.dto.UserDTO;
import com.taru.my_movie_api.models.User;

public interface UserService {

    Boolean existsByUsername(String username);

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(int userId, UserDTO user);

    UserDTO getByUsername(String username);

    UserDTO findByEmail(String email);

    void deleteUserByUsername(String username);
}
