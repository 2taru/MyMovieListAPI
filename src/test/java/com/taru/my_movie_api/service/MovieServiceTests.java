package com.taru.my_movie_api.service;

import com.taru.my_movie_api.dto.MovieDTO;
import com.taru.my_movie_api.dto.MovieResponse;
import com.taru.my_movie_api.exception.MovieNotFoundException;
import com.taru.my_movie_api.mapper.MovieMapper;
import com.taru.my_movie_api.models.Movie;
import com.taru.my_movie_api.repository.MovieRepository;
import com.taru.my_movie_api.service.impl.MovieServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTests {

    @Mock
    private MovieRepository movieRepository;
    @InjectMocks
    private MovieServiceImpl movieService;

    private Movie movie;
    private MovieDTO movieDTO;

    @BeforeEach
    public void init() {
        movie = Movie.builder()
                .seriesTitle("The Shawshank Redemption")
                .imdbRating(9.3)
                .genre("Drama")
                .runtime("142 min")
                .releasedYear(1994)
                .posterLink("https://example.com/poster")
                .overview("Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.")
                .certificate("R")
                .director("Frank Darabont")
                .star1("Tim Robbins")
                .star2("Morgan Freeman")
                .star3("Bob Gunton")
                .star4("William Sadler")
                .metaScore(80)
                .noOfVotes(2500000)
                .gross("$28.34 million")
                .build();
        movieDTO = MovieMapper.mapToDto(movie);
    }

    @Test
    public void MovieService_FindAll_ReturnsMovieResponse() {

        Page<Movie> movies = Mockito.mock(Page.class);

        when(movieRepository.findAll(Mockito.any(Pageable.class))).thenReturn(movies);

        MovieResponse savedMovie = movieService.getAllMovies(0, 10, "id", "ASC");

        assertThat(savedMovie).isNotNull();
    }

    @Test
    public void MovieService_FindById_ReturnsMovieDto() {

        when(movieRepository.findById(movie.getId())).thenReturn(Optional.ofNullable(movie));

        MovieDTO returnedMovieDto = movieService.getMovieById(movie.getId());

        assertThat(returnedMovieDto).isNotNull();
        assertThat(returnedMovieDto).isEqualTo(movieDTO);
    }

    @Test
    public void MovieService_MovieNotFoundException_ThrowsMovieNotFoundException() {

        Assertions.assertThrows(MovieNotFoundException.class, () -> movieService.getMovieById(1));
    }
}
