package com.taru.my_movie_api.mapper;

import com.taru.my_movie_api.dto.MovieDTO;
import com.taru.my_movie_api.models.Movie;

public class MovieMapper {

    public static MovieDTO mapToDto(Movie movie) {

        return MovieDTO.builder()
                .id(movie.getId())
                .genre(movie.getGenre())
                .certificate(movie.getCertificate())
                .gross(movie.getGross())
                .overview(movie.getOverview())
                .posterLink(movie.getPosterLink())
                .runtime(movie.getRuntime())
                .releasedYear(movie.getReleasedYear())
                .star1(movie.getStar1())
                .star2(movie.getStar2())
                .star3(movie.getStar3())
                .star4(movie.getStar4())
                .seriesTitle(movie.getSeriesTitle())
                .imdbRating(movie.getImdbRating())
                .genre(movie.getGenre())
                .posterLink(movie.getPosterLink())
                .director(movie.getDirector())
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
