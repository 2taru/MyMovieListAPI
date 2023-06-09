package com.taru.my_movie_api.repository;

import com.taru.my_movie_api.models.Movie;
import com.taru.my_movie_api.models.MovieListItem;
import com.taru.my_movie_api.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class MovieListItemRepositoryTests {

    @Autowired
    private MovieListItemRepository movieListItemRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void MovieListItemRepository_SaveAll_ReturnsMovieListItem() {

        // Arrange
        MovieListItem movieListItem = MovieListItem.builder()
                .status("test")
                .score(9)
                .user(User.builder().username("testUser").build())
                .movie(Movie.builder().seriesTitle("testTitle").build())
                .build();

        // Act
        MovieListItem savedMovieListItem = movieListItemRepository.save(movieListItem);

        //Assert
        Assertions.assertNotNull(savedMovieListItem);
        Assertions.assertSame(movieListItem, savedMovieListItem);
    }

    @Test
    public void MovieListItemRepository_FindAll_ReturnsMoreThanOneMovieListItem() {

        // Arrange
        Movie movie1 = Movie.builder().seriesTitle("Test1").imdbRating(9.9).build();
        Movie movie2 = Movie.builder().seriesTitle("Test2").imdbRating(9.8).build();
        movieRepository.save(movie1);
        movieRepository.save(movie2);
        User user = User.builder().username("test").email("test@mail.com").password("test").build();
        userRepository.save(user);

        MovieListItem movieListItem1 =
                MovieListItem.builder().status("test1").score(9).user(user).movie(movie1).build();
        MovieListItem movieListItem2 =
                MovieListItem.builder().status("test2").score(8).user(user).movie(movie2).build();

        movieListItemRepository.save(movieListItem1);
        movieListItemRepository.save(movieListItem2);

        // Act
        List<MovieListItem> movieListItemList = movieListItemRepository.findAll();

        //Assert
        Assertions.assertNotNull(movieListItemList);
        Assertions.assertEquals(2, movieListItemList.size());
        Assertions.assertTrue(movieListItemList.contains(movieListItem1));
        Assertions.assertTrue(movieListItemList.contains(movieListItem2));
    }

    @Test
    public void MovieListItemRepository_FindById_ReturnsMovieListItem() {

        // Arrange
        MovieListItem movieListItem = MovieListItem.builder()
                .status("test")
                .score(9)
                .user(User.builder().username("testUser").build())
                .movie(Movie.builder().seriesTitle("testTitle").build())
                .build();
        movieListItemRepository.save(movieListItem);

        // Act
        Optional<MovieListItem> returnedMovieListItem = movieListItemRepository.findById(movieListItem.getId());

        //Assert
        Assertions.assertTrue(returnedMovieListItem.isPresent());
        Assertions.assertSame(movieListItem, returnedMovieListItem.get());
    }

    @Test
    public void MovieListItemRepository_UpdateById_ReturnsMovieListItem() {

        // Arrange
        MovieListItem movieListItem = MovieListItem.builder()
                .status("test")
                .score(9)
                .user(User.builder().username("testUser").build())
                .movie(Movie.builder().seriesTitle("testTitle").build())
                .build();
        movieListItemRepository.save(movieListItem);

        // Act
        movieListItem.setStatus("updated");
        movieListItemRepository.save(movieListItem);
        Optional<MovieListItem> returnedMovieListItem = movieListItemRepository.findById(movieListItem.getId());

        //Assert
        Assertions.assertTrue(returnedMovieListItem.isPresent());
        Assertions.assertSame(movieListItem, returnedMovieListItem.get());
    }

    @Test
    public void MovieListItemRepository_DeleteById() {

        // Arrange
        MovieListItem movieListItem = MovieListItem.builder()
                .status("test")
                .score(9)
                .user(User.builder().username("testUser").build())
                .movie(Movie.builder().seriesTitle("testTitle").build())
                .build();
        movieListItemRepository.save(movieListItem);

        // Act
        movieListItemRepository.deleteById(movieListItem.getId());
        Optional<MovieListItem> returnedMovieListItem = movieListItemRepository.findById(movieListItem.getId());

        //Assert
        Assertions.assertFalse(returnedMovieListItem.isPresent());
    }

    @Test
    public void MovieListItemRepository_FindAllByUserId_ReturnsMoreThanOneMovieListItem() {

        // Arrange
        Movie movie1 = Movie.builder().seriesTitle("Test1").imdbRating(9.9).build();
        Movie movie2 = Movie.builder().seriesTitle("Test2").imdbRating(9.8).build();
        movieRepository.save(movie1);
        movieRepository.save(movie2);

        User user1 = User.builder().username("test1").email("test1@mail.com").password("test1").build();
        User user2 = User.builder().username("test2").email("test2@mail.com").password("test2").build();
        userRepository.save(user1);
        userRepository.save(user2);

        MovieListItem movieListItem1 =
                MovieListItem.builder().status("test1").score(9).user(user1).movie(movie1).build();
        MovieListItem movieListItem2 =
                MovieListItem.builder().status("test2").score(8).user(user1).movie(movie2).build();
        MovieListItem movieListItem3 =
                MovieListItem.builder().status("test3").score(7).user(user2).movie(movie1).build();

        movieListItemRepository.save(movieListItem1);
        movieListItemRepository.save(movieListItem2);
        movieListItemRepository.save(movieListItem3);

        // Act
        List<MovieListItem> movieListItemList =
                movieListItemRepository.findAllByUserId(user1.getId(), PageRequest.of(0, 5)).getContent();

        //Assert
        Assertions.assertNotNull(movieListItemList);
        Assertions.assertEquals(2, movieListItemList.size());
        Assertions.assertTrue(movieListItemList.contains(movieListItem1));
        Assertions.assertTrue(movieListItemList.contains(movieListItem2));
        Assertions.assertFalse(movieListItemList.contains(movieListItem3));
    }
}
