package com.taru.my_movie_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taru.my_movie_api.dto.MovieDTO;
import com.taru.my_movie_api.dto.MovieResponse;
import com.taru.my_movie_api.service.MovieService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = MovieController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MovieService movieService;
    private MovieDTO movieDTO;

    @BeforeEach
    public void init() {
        movieDTO = MovieDTO.builder()
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
    }

    @Test
    public void MovieController_GetAll_ReturnsMovieResponse() throws Exception {

        MovieResponse movieResponse = MovieResponse.builder()
                .pageNo(0)
                .pageSize(10)
                .totalElements(10)
                .totalPages(1)
                .last(true)
                .content(Collections.singletonList(movieDTO))
                .build();

        when(movieService.getAllMovies(0, 10, "imdbRating", "DESC")).thenReturn(movieResponse);

        ResultActions response = mockMvc.perform(get("/api/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .param("pageNo", "0")
                .param("pageSize", "10")
                .param("sortBy", "imdbRating")
                .param("sortType", "DESC"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.content.size()", CoreMatchers.is(movieResponse.getContent().size())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.content[0].seriesTitle", CoreMatchers.is(movieResponse.getContent().get(0).getSeriesTitle())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.content[0].imdbRating", CoreMatchers.is(movieResponse.getContent().get(0).getImdbRating())));
    }

    @Test
    public void MovieController_GetById_ReturnsMovieDTO() throws Exception {

        when(movieService.getMovieById(1)).thenReturn(movieDTO);

        ResultActions response = mockMvc.perform(get("/api/movie/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movieDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.seriesTitle", CoreMatchers.is(movieDTO.getSeriesTitle())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.imdbRating", CoreMatchers.is(movieDTO.getImdbRating())));
    }
}
