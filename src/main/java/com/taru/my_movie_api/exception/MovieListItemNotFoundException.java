package com.taru.my_movie_api.exception;

public class MovieListItemNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3;

    public MovieListItemNotFoundException(String message) {
        super(message);
    }
}
