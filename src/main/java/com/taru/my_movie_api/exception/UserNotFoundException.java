package com.taru.my_movie_api.exception;

public class UserNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 2;

    public UserNotFoundException(String message){
        super(message);
    }
}
