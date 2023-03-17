package com.taru.my_movie_api.mapper;


import com.taru.my_movie_api.dto.MovieListItemDTO;
import com.taru.my_movie_api.models.MovieListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MovieListItemMapper {

    private final UserMapper userMapper;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieListItemMapper(@Lazy UserMapper userMapper, @Lazy MovieMapper movieMapper) {
        this.userMapper = userMapper;
        this.movieMapper = movieMapper;
    }

    public MovieListItemDTO mapToDto(MovieListItem movieListItem) {

        return MovieListItemDTO.builder()
                .id(movieListItem.getId())
                .score(movieListItem.getScore())
                .status(movieListItem.getStatus())
                .description(movieListItem.getDescription())
                .movieDTO(movieMapper.mapToDto(movieListItem.getMovie()))
                .userDTO(userMapper.mapToDto(movieListItem.getUser()))
                .build();
    }

    public MovieListItem mapToEntity(MovieListItemDTO movieListItemDTO) {
        
        return MovieListItem.builder()
                .id(movieListItemDTO.getId())
                .score(movieListItemDTO.getScore())
                .status(movieListItemDTO.getStatus())
                .description(movieListItemDTO.getDescription())
                .movie(movieMapper.mapToEntity(movieListItemDTO.getMovieDTO()))
                .user(userMapper.mapToEntity(movieListItemDTO.getUserDTO()))
                .build();
    }
}
