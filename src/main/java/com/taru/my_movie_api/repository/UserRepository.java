package com.taru.my_movie_api.repository;

import com.taru.my_movie_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);

    void deleteByUsername(String username);

    Boolean existsByUsername(String username);
}
