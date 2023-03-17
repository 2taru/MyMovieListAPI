package com.taru.my_movie_api.service.impl;

import com.taru.my_movie_api.dto.MovieListItemDTO;
import com.taru.my_movie_api.exception.MovieNotFoundException;
import com.taru.my_movie_api.mapper.MovieListItemMapper;
import com.taru.my_movie_api.mapper.MovieMapper;
import com.taru.my_movie_api.mapper.UserMapper;
import com.taru.my_movie_api.models.Movie;
import com.taru.my_movie_api.models.MovieListItem;
import com.taru.my_movie_api.models.User;
import com.taru.my_movie_api.repository.MovieListItemRepository;
import com.taru.my_movie_api.repository.MovieRepository;
import com.taru.my_movie_api.repository.UserRepository;
import com.taru.my_movie_api.service.MovieListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieListItemServiceImpl implements MovieListItemService {

    private final MovieListItemRepository movieListItemRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    @Autowired
    public MovieListItemServiceImpl(
            MovieListItemRepository movieListItemRepository,
            MovieRepository movieRepository,
            UserRepository userRepository
    ) {

        this.movieListItemRepository = movieListItemRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @Override
    public MovieListItemDTO createMovieListItem(MovieListItemDTO movieListItemDTO, int userId, int movieId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with id = " + userId + " - not found!"));

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie with id = " + movieId + " - not found!"));

        movieListItemDTO.setUserDTO(UserMapper.mapToDto(user));
        movieListItemDTO.setMovieDTO(MovieMapper.mapToDto(movie));

        MovieListItem movieListItem = MovieListItemMapper.mapToEntity(movieListItemDTO);

        MovieListItem savedMovieListItem = movieListItemRepository.save(movieListItem);

        return MovieListItemMapper.mapToDto(savedMovieListItem);
    }
}
