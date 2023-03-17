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
    private final MovieListItemMapper movieListItemMapper;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MovieMapper movieMapper;

    public MovieListItemServiceImpl(
            MovieListItemRepository movieListItemRepository,
            MovieListItemMapper movieListItemMapper,
            MovieRepository movieRepository,
            UserRepository userRepository,
            UserMapper userMapper,
            MovieMapper movieMapper
    ) {

        this.movieListItemRepository = movieListItemRepository;
        this.movieListItemMapper = movieListItemMapper;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.movieMapper = movieMapper;
    }

    @Override
    public MovieListItemDTO createMovieListItem(MovieListItemDTO movieListItemDTO, int userId, int movieId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with id = " + userId + " - not found!"));

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie with id = " + movieId + " - not found!"));

        movieListItemDTO.setUserDTO(userMapper.mapToDto(user));
        movieListItemDTO.setMovieDTO(movieMapper.mapToDto(movie));

        MovieListItem movieListItem = movieListItemMapper.mapToEntity(movieListItemDTO);

        MovieListItem savedMovieListItem = movieListItemRepository.save(movieListItem);

        return movieListItemMapper.mapToDto(savedMovieListItem);
    }
}
