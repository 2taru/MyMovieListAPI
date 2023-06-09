package com.taru.my_movie_api.repository;

import com.taru.my_movie_api.models.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void MovieRepository_SaveAll_ReturnsMovie() {

        // Arrange
        Movie movie = Movie.builder().seriesTitle("Test").imdbRating(9.9).build();

        // Act
        Movie savedMovie = movieRepository.save(movie);

        //Assert
        Assertions.assertNotNull(savedMovie);
        Assertions.assertSame(movie, savedMovie);
    }

    @Test
    public void MovieRepository_FindAll_ReturnsMoreThanOneMovie() {

        // Arrange
        Movie movie1 = Movie.builder().seriesTitle("Test").imdbRating(9.9).build();
        Movie movie2 = Movie.builder().seriesTitle("Test").imdbRating(9.9).build();

        movieRepository.save(movie1);
        movieRepository.save(movie2);

        // Act
        List<Movie> movieList = movieRepository.findAll();

        //Assert
        Assertions.assertNotNull(movieList);
        Assertions.assertEquals(2, movieList.size());
        Assertions.assertTrue(movieList.contains(movie1));
        Assertions.assertTrue(movieList.contains(movie2));
    }

    @Test
    public void MovieRepository_FindById_ReturnsMovie() {

        // Arrange
        Movie movie = Movie.builder().seriesTitle("Test").imdbRating(9.9).build();
        movieRepository.save(movie);

        // Act
        Optional<Movie> returnedMovie = movieRepository.findById(movie.getId());

        //Assert
        Assertions.assertTrue(returnedMovie.isPresent());
        Assertions.assertSame(movie, returnedMovie.get());
    }

    @Test
    public void MovieRepository_UpdateById_ReturnsMovie() {

        // Arrange
        Movie movie = Movie.builder().seriesTitle("Test").imdbRating(9.9).build();
        movieRepository.save(movie);

        // Act
        movie.setSeriesTitle("Updated");
        movieRepository.save(movie);
        Optional<Movie> returnedMovie = movieRepository.findById(movie.getId());

        //Assert
        Assertions.assertTrue(returnedMovie.isPresent());
        Assertions.assertSame(movie, returnedMovie.get());
    }

    @Test
    public void MovieRepository_DeleteById() {

        // Arrange
        Movie movie = Movie.builder().seriesTitle("Test").imdbRating(9.9).build();
        movieRepository.save(movie);

        // Act
        movieRepository.deleteById(movie.getId());
        Optional<Movie> returnedMovie = movieRepository.findById(movie.getId());

        //Assert
        Assertions.assertFalse(returnedMovie.isPresent());
    }
}
