package com.taru.my_movie_api.controller;

import com.taru.my_movie_api.dto.MovieListItemDTO;
import com.taru.my_movie_api.dto.MovieListItemResponse;
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

    @GetMapping("/movieListItem")
    public ResponseEntity<MovieListItemResponse> getMovieListItems(
            @RequestParam(value = "userId") int userId,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {

        MovieListItemResponse movieListItemResponse =
                movieListItemService.getAllMovieListItemsByUserId(userId, pageNo, pageSize);

        return new ResponseEntity<>(movieListItemResponse, HttpStatus.OK);
    }

    @GetMapping("/movieListItem/{id}")
    public ResponseEntity<MovieListItemDTO> getMovieListItemById(@PathVariable("id") int id) {

        return new ResponseEntity<>(movieListItemService.getMovieListItemById(id), HttpStatus.OK);
    }

    @PutMapping("/movieListItem/{id}")
    public ResponseEntity<MovieListItemDTO> updateMovieListItemById(
            @RequestBody MovieListItemDTO movieListItemDTO,
            @PathVariable("id") int id
    ) {

        return new ResponseEntity<>(movieListItemService.updateMovieListItemById(id, movieListItemDTO), HttpStatus.OK);
    }
}
