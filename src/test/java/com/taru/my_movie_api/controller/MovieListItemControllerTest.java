package com.taru.my_movie_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taru.my_movie_api.dto.MovieListItemDTO;
import com.taru.my_movie_api.dto.MovieListItemResponse;
import com.taru.my_movie_api.service.MovieListItemService;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@WebMvcTest(controllers = MovieListItemController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class MovieListItemControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MovieListItemService movieListItemService;

    private MovieListItemDTO movieListItemDTO;

    @BeforeEach
    public void init() {
        movieListItemDTO = MovieListItemDTO.builder().status("Completed").score(10).description("Great movie!").build();
    }

    @Test
    public void MovieListItemController_Create_ReturnsString() throws Exception {

        when(movieListItemService.createMovieListItem(movieListItemDTO, 1, 1)).thenReturn(movieListItemDTO);

        ResultActions response = mockMvc.perform(post("/api/movieListItem/create")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId", "1")
                .param("movieId", "1")
                .content(objectMapper.writeValueAsString(movieListItemDTO)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is("Created")));
    }

    @Test
    public void MovieListItemController_GetAllByUserId_ReturnsMovieListItemResponse() throws Exception {

        MovieListItemResponse movieListItemResponse = MovieListItemResponse.builder()
                .pageNo(0)
                .pageSize(10)
                .totalElements(10)
                .totalPages(1)
                .last(true)
                .content(Collections.singletonList(movieListItemDTO))
                .build();

        when(movieListItemService.getAllMovieListItemsByUserId(1, 0, 10, "id", "DESC"))
                .thenReturn(movieListItemResponse);

        ResultActions response = mockMvc.perform(get("/api/movieListItem")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId", "1")
                .param("pageNo", "0")
                .param("pageSize", "10")
                .param("sortBy", "id")
                .param("sortType", "DESC"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.content.size()", CoreMatchers.is(movieListItemResponse.getContent().size())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.content[0].status", CoreMatchers.is(movieListItemResponse.getContent().get(0).getStatus())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.content[0].score", CoreMatchers.is(movieListItemResponse.getContent().get(0).getScore())));
    }

    @Test
    public void MovieListItemController_GetById_ReturnsMovieListItemDTO() throws Exception {

        when(movieListItemService.getMovieListItemById(1)).thenReturn(movieListItemDTO);

        ResultActions response = mockMvc.perform(get("/api/movieListItem/1").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movieListItemDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", CoreMatchers.is(movieListItemDTO.getStatus())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.score", CoreMatchers.is(movieListItemDTO.getScore())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(movieListItemDTO.getDescription())));
    }

    @Test
    public void MovieListItemController_UpdateById_ReturnsMovieListItemDTO() throws Exception {

        when(movieListItemService.updateMovieListItemById(1, movieListItemDTO)).thenReturn(movieListItemDTO);

        ResultActions response = mockMvc.perform(put("/api/movieListItem/1/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movieListItemDTO)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", CoreMatchers.is(movieListItemDTO.getStatus())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.score", CoreMatchers.is(movieListItemDTO.getScore())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(movieListItemDTO.getDescription())));
    }

    @Test
    public void MovieListItemController_DeleteById_ReturnsString() throws Exception {

        doNothing().when(movieListItemService).deleteMovieListItemById(1);

        ResultActions response = mockMvc.perform(delete("/api/movieListItem/1/delete")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is("Deleted MovieListItem with id = 1")));
    }
}
