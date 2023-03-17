package com.taru.my_movie_api.service.impl;

import com.taru.my_movie_api.dto.MovieDTO;
import com.taru.my_movie_api.dto.MovieResponse;
import com.taru.my_movie_api.exception.MovieNotFoundException;
import com.taru.my_movie_api.mapper.MovieMapper;
import com.taru.my_movie_api.models.Movie;
import com.taru.my_movie_api.repository.MovieRepository;
import com.taru.my_movie_api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper) {

        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    @Override
    public MovieResponse getAllMovies(int pageNo, int pageSize, String sortBy, String sortType) {

        Page<Movie> movies = movieRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(sortType.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy)));
        List<Movie> listOfMovies = movies.getContent();
        List<MovieDTO> content = listOfMovies.stream().map(movieMapper::mapToDto).collect(Collectors.toList());

        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setContent(content);
        movieResponse.setPageNo(movies.getNumber());
        movieResponse.setPageSize(movies.getSize());
        movieResponse.setTotalElements(movies.getTotalElements());
        movieResponse.setTotalPages(movies.getTotalPages());
        movieResponse.setLast(movies.isLast());

        return movieResponse;
    }

    @Override
    public MovieDTO getMovieById(int movieId) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie with id = " + movieId + " - not found!"));
        
        return movieMapper.mapToDto(movie);
    }
}
