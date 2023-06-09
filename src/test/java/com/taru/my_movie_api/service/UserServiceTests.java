package com.taru.my_movie_api.service;

import com.taru.my_movie_api.dto.UserDTO;
import com.taru.my_movie_api.exception.UserNotFoundException;
import com.taru.my_movie_api.mapper.UserMapper;
import com.taru.my_movie_api.models.User;
import com.taru.my_movie_api.repository.UserRepository;
import com.taru.my_movie_api.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    public void init() {
        user = User.builder()
                .username("exampleUser")
                .email("example@example.com")
                .password("password123")
                .roles(new ArrayList<>())
                .movieListItems(new ArrayList<>())
                .build();
        userDTO = UserMapper.mapToDto(user);
    }

    @Test
    public void UserService_Create_ReturnsUserDto() {

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        UserDTO savedUserDto = userService.createUser(userDTO);

        assertThat(savedUserDto).isNotNull();
        assertThat(savedUserDto).isEqualTo(userDTO);
    }

    @Test
    public void UserService_FindById_ReturnsUserDto() {

        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));

        UserDTO returnedUserDto = userService.getUserById(1);

        assertThat(returnedUserDto).isNotNull();
        assertThat(returnedUserDto).isEqualTo(userDTO);
    }

    @Test
    public void UserService_UpdateById_ReturnsUserDto() {

        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        UserDTO updatedUserDto = userService.updateUserById(1, userDTO);

        assertThat(updatedUserDto).isNotNull();
        assertThat(updatedUserDto).isEqualTo(userDTO);
    }

    @Test
    public void UserService_DeleteById() {

        assertAll(() -> userService.deleteUserById(1));
    }

    @Test
    public void UserService_ExistsByUsername_ReturnsTrueIfExists() {

        when(userRepository.existsByUsername("test")).thenReturn(true);

        boolean userExists = userService.existsByUsername("test");

        assertThat(userExists).isTrue();
    }

    @Test
    public void UserService_UserNotFoundException_ThrowsUserNotFoundException() {

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.getUserById(1));
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.updateUserById(1, new UserDTO()));
    }
}
