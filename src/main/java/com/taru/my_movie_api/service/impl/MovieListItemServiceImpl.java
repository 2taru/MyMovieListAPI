package com.taru.my_movie_api.service.impl;

import com.taru.my_movie_api.dto.MovieListItemDTO;
import com.taru.my_movie_api.dto.MovieListItemResponse;
import com.taru.my_movie_api.exception.MovieListItemNotFoundException;
import com.taru.my_movie_api.exception.MovieNotFoundException;
import com.taru.my_movie_api.exception.UserNotFoundException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new UserNotFoundException("User with id = " + userId + " - not found!"));

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie with id = " + movieId + " - not found!"));

        movieListItemDTO.setUserDTO(UserMapper.mapToDto(user));
        movieListItemDTO.setMovieDTO(MovieMapper.mapToDto(movie));

        MovieListItem movieListItem = MovieListItemMapper.mapToEntity(movieListItemDTO);

        MovieListItem savedMovieListItem = movieListItemRepository.save(movieListItem);

        return MovieListItemMapper.mapToDto(savedMovieListItem);
    }

    @Override
    public MovieListItemResponse getAllMovieListItemsByUserId(int userId, int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<MovieListItem> movieListItems = movieListItemRepository.findAllByUserId(userId, pageable);
        List<MovieListItem> movieListItemList = movieListItems.getContent();
        List<MovieListItemDTO> content = movieListItemList.stream().map(MovieListItemMapper::mapToDto).collect(Collectors.toList());

        MovieListItemResponse movieListItemResponse = new MovieListItemResponse();
        movieListItemResponse.setContent(content);
        movieListItemResponse.setPageNo(movieListItems.getNumber());
        movieListItemResponse.setPageSize(movieListItems.getSize());
        movieListItemResponse.setTotalElements(movieListItems.getTotalElements());
        movieListItemResponse.setTotalPages(movieListItems.getTotalPages());
        movieListItemResponse.setLast(movieListItems.isLast());

        return movieListItemResponse;
    }

    @Override
    public MovieListItemDTO getMovieListItemById(int movieListItemId) {

        MovieListItem movieListItem = movieListItemRepository.findById(movieListItemId)
                .orElseThrow(() -> new MovieListItemNotFoundException("MovieListItem with id = " + movieListItemId + " - not found!"));

        return MovieListItemMapper.mapToDto(movieListItem);
    }

    @Override
    public MovieListItemDTO updateMovieListItemById(int movieListItemId, MovieListItemDTO movieListItemDTO) {

        MovieListItem movieListItem = movieListItemRepository.findById(movieListItemId)
                .orElseThrow(() -> new MovieListItemNotFoundException("MovieListItem with id = " + movieListItemId + " - not found!"));

        movieListItem.setDescription(movieListItemDTO.getDescription());
        movieListItem.setScore(movieListItemDTO.getScore());
        movieListItem.setStatus(movieListItemDTO.getStatus());
        movieListItem.setUser(UserMapper.mapToEntity(movieListItemDTO.getUserDTO()));
        movieListItem.setMovie(MovieMapper.mapToEntity(movieListItemDTO.getMovieDTO()));

        MovieListItem updatedMovieListItem = movieListItemRepository.save(movieListItem);

        return MovieListItemMapper.mapToDto(updatedMovieListItem);
    }
}
