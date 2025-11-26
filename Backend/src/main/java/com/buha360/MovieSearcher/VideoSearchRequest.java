package com.buha360.MovieSearcher;

public record VideoSearchRequest(
        String title,
        String category,
        String genre,
        String language,
        String maturityRating,
        Double minImdbRating,
        Double maxImdbRating,
        Integer releaseYearFrom,
        Integer releaseYearTo
) {}