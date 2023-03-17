package com.taru.my_movie_api.exception;

public class MovieNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public MovieNotFoundException(String message) {
        super(message);
    }
}
