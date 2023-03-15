package com.taru.my_movie_api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue
    private int id;
    private String seriesTitle;
    private double imdbRating;
    private String genre;
    private String runtime;
    private int releasedYear;
    private String posterLink;
    private String overview;
    private String certificate;
    private String director;
    private String star1;
    private String star2;
    private String star3;
    private String star4;
    private int metaScore;
    private int noOfVotes;
    private String gross;
}
