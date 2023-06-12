package com.taru.my_movie_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taru.my_movie_api.dto.UserDTO;
import com.taru.my_movie_api.service.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;

    private UserDTO userDTO;

    @BeforeEach
    public void init() {
        userDTO = UserDTO.builder()
                .username("exampleUser")
                .email("example@example.com")
                .password("password123")
                .build();
    }

    @Test
    public void UserController_Create_ReturnsString() throws Exception {

        when(userService.createUser(Mockito.any(UserDTO.class))).thenReturn(userDTO);

        ResultActions response = mockMvc.perform(post("/api/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is("User registered!")));

        when(userService.existsByUsername(Mockito.any(String.class))).thenReturn(true);

        response = mockMvc.perform(post("/api/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void UserController_GetById_ReturnsUserDTO() throws Exception {

        when(userService.getUserById(1)).thenReturn(userDTO);

        ResultActions response = mockMvc.perform(get("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", CoreMatchers.is(userDTO.getUsername())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(userDTO.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", CoreMatchers.is(userDTO.getPassword())));
    }

    @Test
    public void UserController_UpdateById_ReturnsUserDTO() throws Exception {

        when(userService.updateUserById(1, userDTO)).thenReturn(userDTO);

        ResultActions response = mockMvc.perform(put("/api/user/1/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", CoreMatchers.is(userDTO.getUsername())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(userDTO.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", CoreMatchers.is(userDTO.getPassword())));
    }

    @Test
    public void UserController_DeleteById_ReturnsString() throws Exception {

        doNothing().when(userService).deleteUserById(1);

        ResultActions response = mockMvc.perform(delete("/api/user/1/delete")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is("Deleted user with id = 1")));
    }
}
