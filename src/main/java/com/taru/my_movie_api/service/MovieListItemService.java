package com.taru.my_movie_api.service;

import com.taru.my_movie_api.dto.MovieListItemDTO;
import com.taru.my_movie_api.dto.MovieListItemResponse;

public interface MovieListItemService {

    MovieListItemDTO createMovieListItem(MovieListItemDTO movieListItemDTO, int userId, int movieId);

    MovieListItemResponse getAllMovieListItemsByUserId(int userId, int pageNo, int pageSize);

    MovieListItemDTO getMovieListItemById(int movieListItemId);

    MovieListItemDTO updateMovieListItemById(int movieListItemId, MovieListItemDTO movieListItemDTO);

    void deleteMovieListItemById(int movieListItemId);
}
