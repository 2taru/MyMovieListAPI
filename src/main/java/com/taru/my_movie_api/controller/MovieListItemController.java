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
    public ResponseEntity<MovieListItemResponse> getMovieListItemsByUserId(
            @RequestParam(value = "userId") int userId,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortType", defaultValue = "DESC", required = false) String sortType
    ) {

        MovieListItemResponse movieListItemResponse =
                movieListItemService.getAllMovieListItemsByUserId(userId, pageNo, pageSize, sortBy, sortType);

        return new ResponseEntity<>(movieListItemResponse, HttpStatus.OK);
    }

    @GetMapping("/movieListItem/{id}")
    public ResponseEntity<MovieListItemDTO> getMovieListItemById(@PathVariable("id") int id) {

        return new ResponseEntity<>(movieListItemService.getMovieListItemById(id), HttpStatus.OK);
    }

    @PutMapping("/movieListItem/{id}/update")
    public ResponseEntity<MovieListItemDTO> updateMovieListItemById(
            @RequestBody MovieListItemDTO movieListItemDTO,
            @PathVariable("id") int id
    ) {

        return new ResponseEntity<>(movieListItemService.updateMovieListItemById(id, movieListItemDTO), HttpStatus.OK);
    }

    @DeleteMapping("/movieListItem/{id}/delete")
    public ResponseEntity<String> deleteMovieListItemById(@PathVariable("id") int id) {

        movieListItemService.deleteMovieListItemById(id);

        return new ResponseEntity<>("Deleted MovieListItem with id = " + id, HttpStatus.OK);
    }
}
