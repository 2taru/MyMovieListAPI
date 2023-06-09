package com.taru.my_movie_api.service;

import com.taru.my_movie_api.dto.MovieListItemDTO;
import com.taru.my_movie_api.dto.MovieListItemResponse;
import com.taru.my_movie_api.exception.MovieListItemNotFoundException;
import com.taru.my_movie_api.mapper.MovieListItemMapper;
import com.taru.my_movie_api.models.Movie;
import com.taru.my_movie_api.models.MovieListItem;
import com.taru.my_movie_api.models.User;
import com.taru.my_movie_api.repository.MovieListItemRepository;
import com.taru.my_movie_api.repository.MovieRepository;
import com.taru.my_movie_api.repository.UserRepository;
import com.taru.my_movie_api.service.impl.MovieListItemServiceImpl;
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

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieListItemServiceTests {

    @Mock
    private MovieListItemRepository movieListItemRepository;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private MovieListItemServiceImpl movieListItemService;

    private User user;
    private Movie movie;
    private MovieListItem movieListItem;
    private MovieListItemDTO movieListItemDTO;

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
        user = User.builder()
                .username("exampleUser")
                .email("example@example.com")
                .password("password123")
                .roles(new ArrayList<>())
                .movieListItems(new ArrayList<>())
                .build();
        movieListItem = MovieListItem.builder()
                .status("Completed")
                .score(10)
                .description("Great movie!")
                .user(user)
                .movie(movie)
                .build();
        movieListItemDTO = MovieListItemMapper.mapToDto(movieListItem);
    }

    @Test
    public void MovieListItemService_Create_ReturnsMovieListItemDto() {

        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));
        when(movieRepository.findById(movie.getId())).thenReturn(Optional.ofNullable(movie));
        when(movieListItemRepository.save(Mockito.any(MovieListItem.class))).thenReturn(movieListItem);

        MovieListItemDTO returnedMovieListItemDTO =
                movieListItemService.createMovieListItem(movieListItemDTO, user.getId(), movie.getId());

        assertThat(returnedMovieListItemDTO).isNotNull();
        assertThat(returnedMovieListItemDTO).isEqualTo(movieListItemDTO);
    }

    @Test
    public void MovieListItemService_FindAllByUserId_ReturnsMovieListItemResponse() {

        Page<MovieListItem> movieListItems = Mockito.mock(Page.class);

        when(movieListItemRepository.findAllByUserId(Mockito.any(Integer.class), Mockito.any(Pageable.class))).thenReturn(movieListItems);

        MovieListItemResponse savedMovieListItems =
                movieListItemService.getAllMovieListItemsByUserId(user.getId(), 0, 10, "id", "ASC");

        assertThat(savedMovieListItems).isNotNull();
    }

    @Test
    public void MovieListItemService_FindById_ReturnsMovieListItemDto() {

        when(movieListItemRepository.findById(1)).thenReturn(Optional.ofNullable(movieListItem));

        MovieListItemDTO returnedMovieListItemDTO = movieListItemService.getMovieListItemById(1);

        assertThat(returnedMovieListItemDTO).isNotNull();
        assertThat(returnedMovieListItemDTO).isEqualTo(movieListItemDTO);
    }

    @Test
    public void MovieListItemService_UpdateById_ReturnsMovieListItemDto() {

        when(movieListItemRepository.findById(1)).thenReturn(Optional.ofNullable(movieListItem));
        when(movieListItemRepository.save(Mockito.any(MovieListItem.class))).thenReturn(movieListItem);

        MovieListItemDTO updatedMovieListItemDTO = movieListItemService.updateMovieListItemById(1, movieListItemDTO);

        assertThat(updatedMovieListItemDTO).isNotNull();
        assertThat(updatedMovieListItemDTO).isEqualTo(movieListItemDTO);
    }

    @Test
    public void MovieListItemService_DeleteById() {

        assertAll(() -> movieListItemService.deleteMovieListItemById(1));
    }


    @Test
    public void MovieListItemService_MovieListItemNotFoundException_ThrowsMovieListItemNotFoundException() {

        Assertions.assertThrows(MovieListItemNotFoundException.class, () -> movieListItemService.getMovieListItemById(1));
        Assertions.assertThrows(MovieListItemNotFoundException.class, () -> movieListItemService.updateMovieListItemById(1, new MovieListItemDTO()));
    }
}
