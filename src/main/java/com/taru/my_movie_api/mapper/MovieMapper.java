package com.taru.my_movie_api.mapper;

import com.taru.my_movie_api.dto.MovieDTO;
import com.taru.my_movie_api.models.Movie;

public class MovieMapper {
    public static MovieDTO mapToDto(Movie pokemon) {

        return MovieDTO.builder()
                .id(pokemon.getId())
                .genre(pokemon.getGenre())
                .certificate(pokemon.getCertificate())
                .gross(pokemon.getGross())
                .overview(pokemon.getOverview())
                .posterLink(pokemon.getPosterLink())
                .runtime(pokemon.getRuntime())
                .releasedYear(pokemon.getReleasedYear())
                .star1(pokemon.getStar1())
                .star2(pokemon.getStar2())
                .star3(pokemon.getStar3())
                .star4(pokemon.getStar4())
                .seriesTitle(pokemon.getSeriesTitle())
                .imdbRating(pokemon.getImdbRating())
                .genre(pokemon.getGenre())
                .posterLink(pokemon.getPosterLink())
                .director(pokemon.getDirector())
                .build();
    }

    public static Movie mapToEntity(MovieDTO movieDTO) {

        return Movie.builder()
                .id(movieDTO.getId())
                .genre(movieDTO.getGenre())
                .certificate(movieDTO.getCertificate())
                .gross(movieDTO.getGross())
                .overview(movieDTO.getOverview())
                .posterLink(movieDTO.getPosterLink())
                .runtime(movieDTO.getRuntime())
                .releasedYear(movieDTO.getReleasedYear())
                .star1(movieDTO.getStar1())
                .star2(movieDTO.getStar2())
                .star3(movieDTO.getStar3())
                .star4(movieDTO.getStar4())
                .seriesTitle(movieDTO.getSeriesTitle())
                .imdbRating(movieDTO.getImdbRating())
                .genre(movieDTO.getGenre())
                .posterLink(movieDTO.getPosterLink())
                .director(movieDTO.getDirector())
                .build();
    }
}
