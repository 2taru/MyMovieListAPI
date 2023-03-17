package com.taru.my_movie_api.service;

import com.taru.my_movie_api.dto.MovieListItemDTO;

public interface MovieListItemService {

    MovieListItemDTO createMovieListItem(MovieListItemDTO movieListItemDTO, int userId, int movieId);
}
