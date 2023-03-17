package com.taru.my_movie_api.controller;

import com.taru.my_movie_api.dto.MovieListItemDTO;
import com.taru.my_movie_api.service.MovieListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MovieListItemController {

    private final MovieListItemService movieListItemService;

    @Autowired
    public MovieListItemController(MovieListItemService movieListItemService) {
        this.movieListItemService = movieListItemService;
    }

    @PostMapping("/movieListItem/create")
    public ResponseEntity<String> createMovieListItem(
            @RequestBody MovieListItemDTO movieListItemDTO,
            @RequestParam(value = "userId") int userId,
            @RequestParam(value = "movieId") int movieId
    ) {

        movieListItemService.createMovieListItem(movieListItemDTO, userId, movieId);

        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }
}
