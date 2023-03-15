package com.taru.my_movie_api.service;

import com.taru.my_movie_api.dto.MovieResponse;

public interface MovieService {
    MovieResponse getAllMovies(int pageNo, int pageSize);
}
