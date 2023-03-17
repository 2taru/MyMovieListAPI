package com.taru.my_movie_api.mapper;

import com.taru.my_movie_api.dto.MovieListItemDTO;
import com.taru.my_movie_api.models.MovieListItem;

public class MovieListItemMapper {

    public static MovieListItemDTO mapToDto(MovieListItem movieListItem) {

        return MovieListItemDTO.builder()
                .id(movieListItem.getId())
                .score(movieListItem.getScore())
                .status(movieListItem.getStatus())
                .description(movieListItem.getDescription())
                .movieDTO(MovieMapper.mapToDto(movieListItem.getMovie()))
                .userDTO(UserMapper.mapToDto(movieListItem.getUser()))
                .build();
    }

    public static MovieListItem mapToEntity(MovieListItemDTO movieListItemDTO) {

        return MovieListItem.builder()
                .id(movieListItemDTO.getId())
                .score(movieListItemDTO.getScore())
                .status(movieListItemDTO.getStatus())
                .description(movieListItemDTO.getDescription())
                .movie(MovieMapper.mapToEntity(movieListItemDTO.getMovieDTO()))
                .user(UserMapper.mapToEntity(movieListItemDTO.getUserDTO()))
                .build();
    }
}
