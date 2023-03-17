package com.taru.my_movie_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieListItemDTO {

    private int id;
    private String status;
    private int score;
    private String description;
    private UserDTO userDTO;
    private MovieDTO movieDTO;
}
