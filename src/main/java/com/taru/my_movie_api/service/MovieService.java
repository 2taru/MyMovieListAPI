package com.taru.my_movie_api.service;

import com.taru.my_movie_api.dto.MovieResponse;
import com.taru.my_movie_api.models.Movie;

import java.util.List;

public interface MovieService {
    MovieResponse getAllMovies(int pageNo, int pageSize);
}
