package com.taru.my_movie_api.repository;

import com.taru.my_movie_api.models.MovieListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieListItemRepository extends JpaRepository<MovieListItem, Integer> {
}
