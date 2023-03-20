package com.taru.my_movie_api.repository;

import com.taru.my_movie_api.models.MovieListItem;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

public interface MovieListItemRepository extends JpaRepository<MovieListItem, Integer> {

    Page<MovieListItem> findAllByUserId(int userId, Pageable pageable);
}
