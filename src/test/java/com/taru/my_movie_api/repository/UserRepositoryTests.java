package com.taru.my_movie_api.repository;


import com.taru.my_movie_api.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepository_SaveAll_ReturnedSavedUser() {

        // Arrange
        User user = User.builder().username("test").email("test@mail.com").password("test").build();

        // Act
        User savedUser = userRepository.save(user);

        //Assert
        Assertions.assertNotNull(savedUser);
        Assertions.assertSame(user, savedUser);
    }

    @Test
    public void UserRepository_FindAll_ReturnMoreThanOneUser() {

        // Arrange
        User user1 = User.builder().username("test").email("test@mail.com").password("test").build();
        User user2 = User.builder().username("test").email("test@mail.com").password("test").build();

        userRepository.save(user1);
        userRepository.save(user2);

        // Act
        List<User> userList = userRepository.findAll();

        //Assert
        Assertions.assertNotNull(userList);
        Assertions.assertEquals(2, userList.size());
        Assertions.assertTrue(userList.contains(user1));
        Assertions.assertTrue(userList.contains(user2));
    }

    @Test
    public void UserRepository_FindById_ReturnCorrectUser() {

        // Arrange
        User user = User.builder().username("test").email("test@mail.com").password("test").build();
        userRepository.save(user);

        // Act
        Optional<User> returnedUser = userRepository.findById(user.getId());

        //Assert
        Assertions.assertTrue(returnedUser.isPresent());
        Assertions.assertSame(user, returnedUser.get());
    }

    @Test
    public void UserRepository_UpdateById_ReturnUpdatedUser() {

        // Arrange
        User user = User.builder().username("test").email("test@mail.com").password("test").build();
        userRepository.save(user);

        // Act
        user.setUsername("updated");
        userRepository.save(user);
        Optional<User> returnedUser = userRepository.findById(user.getId());

        //Assert
        Assertions.assertTrue(returnedUser.isPresent());
        Assertions.assertSame(user, returnedUser.get());
    }

    @Test
    public void UserRepository_DeleteById_DeleteUserById() {

        // Arrange
        User user = User.builder().username("test").email("test@mail.com").password("test").build();
        userRepository.save(user);

        // Act
        userRepository.deleteById(user.getId());
        Optional<User> returnedUser = userRepository.findById(user.getId());

        //Assert
        Assertions.assertFalse(returnedUser.isPresent());
    }

    @Test
    public void UserRepository_ExistsByUsername_ReturnedIfExist() {

        // Arrange
        User user = User.builder().username("test").email("test@mail.com").password("test").build();
        userRepository.save(user);

        // Act
        boolean userExist = userRepository.existsByUsername(user.getUsername());

        //Assert
        Assertions.assertTrue(userExist);
    }

    @Test
    public void UserRepository_FindByUsername_ReturnedCorrectUser() {

        // Arrange
        User user = User.builder().username("test").email("test@mail.com").password("test").build();
        userRepository.save(user);

        // Act
        Optional<User> returnedUser = userRepository.findByUsername(user.getUsername());

        //Assert
        Assertions.assertTrue(returnedUser.isPresent());
        Assertions.assertSame(user, returnedUser.get());
    }
}
