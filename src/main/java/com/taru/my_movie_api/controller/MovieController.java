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
@RequestMapping("/api/")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("movie")
    public ResponseEntity<MovieResponse> getMovies(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {

        return new ResponseEntity<>(movieService.getAllMovies(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("movie/{id}")
    public ResponseEntity<MovieDTO> pokemonDetail(@PathVariable int id) {

        return ResponseEntity.ok(movieService.getMovieById(id));
    }
}
