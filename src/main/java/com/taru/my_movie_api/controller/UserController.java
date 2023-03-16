package com.taru.my_movie_api.controller;

import com.taru.my_movie_api.dto.UserDTO;
import com.taru.my_movie_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping("/user/create")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {

        if (userService.existsByUsername(userDTO.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        userService.createUser(userDTO);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }

    @PutMapping("/user/{id}/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable("id") int userId) {

        UserDTO response = userService.updateUser(userId, userDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
