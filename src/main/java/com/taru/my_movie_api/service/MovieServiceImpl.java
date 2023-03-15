package com.taru.my_movie_api.service;

import com.taru.my_movie_api.dto.MovieDTO;
import com.taru.my_movie_api.dto.MovieResponse;
import com.taru.my_movie_api.models.Movie;
import com.taru.my_movie_api.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService{
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieResponse getAllMovies(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Movie> movies = movieRepository.findAll(pageable);
        List<Movie> listOfMovies = movies.getContent();
        List<MovieDTO> content = listOfMovies.stream().map(this::mapToDto).collect(Collectors.toList());

        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setContent(content);
        movieResponse.setPageNo(movies.getNumber());
        movieResponse.setPageSize(movies.getSize());
        movieResponse.setTotalElements(movies.getTotalElements());
        movieResponse.setTotalPages(movies.getTotalPages());
        movieResponse.setLast(movies.isLast());

        return movieResponse;
    }

    private MovieDTO mapToDto(Movie pokemon) {

        return MovieDTO.builder()
                .id(pokemon.getId())
                .genre(pokemon.getGenre())
                .certificate(pokemon.getCertificate())
                .gross(pokemon.getGross())
                .overview(pokemon.getOverview())
                .posterLink(pokemon.getPosterLink())
                .runtime(pokemon.getRuntime())
                .releasedYear(pokemon.getReleasedYear())
                .star1(pokemon.getStar1())
                .star2(pokemon.getStar2())
                .star3(pokemon.getStar3())
                .star4(pokemon.getStar4())
                .seriesTitle(pokemon.getSeriesTitle())
                .imdbRating(pokemon.getImdbRating())
                .genre(pokemon.getGenre())
                .posterLink(pokemon.getPosterLink())
                .director(pokemon.getDirector())
                .build();
    }

    private Movie mapToEntity(MovieDTO movieDTO) {

        return Movie.builder()
                .id(movieDTO.getId())
                .genre(movieDTO.getGenre())
                .certificate(movieDTO.getCertificate())
                .gross(movieDTO.getGross())
                .overview(movieDTO.getOverview())
                .posterLink(movieDTO.getPosterLink())
                .runtime(movieDTO.getRuntime())
                .releasedYear(movieDTO.getReleasedYear())
                .star1(movieDTO.getStar1())
                .star2(movieDTO.getStar2())
                .star3(movieDTO.getStar3())
                .star4(movieDTO.getStar4())
                .seriesTitle(movieDTO.getSeriesTitle())
                .imdbRating(movieDTO.getImdbRating())
                .genre(movieDTO.getGenre())
                .posterLink(movieDTO.getPosterLink())
                .director(movieDTO.getDirector())
                .build();
    }
}
