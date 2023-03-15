package com.taru.my_movie_api.repository;

import com.taru.my_movie_api.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
