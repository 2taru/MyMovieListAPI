package com.taru.my_movie_api.controller;

import com.taru.my_movie_api.dto.MovieDTO;
import com.taru.my_movie_api.dto.MovieResponse;
import com.taru.my_movie_api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {

        this.movieService = movieService;
    }

    @GetMapping("/movie")
    public ResponseEntity<MovieResponse> getAllMovies(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "imdbRating", required = false) String sortBy,
            @RequestParam(value = "sortType", defaultValue = "DESC", required = false) String sortType
    ) {

        return new ResponseEntity<>(movieService.getAllMovies(pageNo, pageSize, sortBy, sortType), HttpStatus.OK);
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable int id) {

        return ResponseEntity.ok(movieService.getMovieById(id));
    }
}
