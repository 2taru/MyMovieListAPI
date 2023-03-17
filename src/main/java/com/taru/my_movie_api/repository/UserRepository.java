package com.taru.my_movie_api.repository;

import com.taru.my_movie_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Boolean existsByUsername(String username);
}
