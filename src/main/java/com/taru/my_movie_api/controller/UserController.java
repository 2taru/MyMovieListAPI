package com.taru.my_movie_api.controller;

import com.taru.my_movie_api.dto.UserDTO;
import com.taru.my_movie_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        UserDTO response = userService.updateUserById(userId, userDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") int userId) {

        UserDTO response = userService.getUserById(userId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> DeleteUser(@PathVariable("id") int userId) {

        userService.deleteUserById(userId);

        return new ResponseEntity<>("Deleted user with id = " + userId, HttpStatus.OK);
    }
}
