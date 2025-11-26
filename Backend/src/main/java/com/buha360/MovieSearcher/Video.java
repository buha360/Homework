package com.buha360.MovieSearcher;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "VIDEOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(length = 100)
    private String category;       // MOVIE, TV_SERIES, DOCUMENTARY

    @Column(length = 100)
    private String genre;          // Action, Comedy, Drama

    @Column(length = 20)
    private String language;       // en, hu, de

    @Column(name = "PRODUCTION_COMPANY", length = 200)
    private String productionCompany;

    @Column(name = "DESCRIPTION", length = 2000)
    private String description;

    @Column(name = "MATURITY_RATING", length = 20)
    private String maturityRating; // 12, 16, 18, PG-13, stb.

    private Integer releaseYear;

    @Column(name = "MAIN_CAST", length = 1000)
    private String mainCast;       // "Keanu Reeves, ..."

    @Column(length = 200)
    private String director;

    private Double imdbRating;     // pl. 8.7

    @Column(length = 500)
    private String thumbnailUrl;   // indexkép

    @Column(length = 500)
    private String introVideoUrl;  // YouTube trailer / intro link
}