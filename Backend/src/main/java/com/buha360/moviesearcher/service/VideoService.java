package com.buha360.moviesearcher.service;

import com.buha360.moviesearcher.VideoSearchRequest;
import com.buha360.moviesearcher.model.QVideo;
import com.buha360.moviesearcher.model.Video;
import com.buha360.moviesearcher.repository.VideoRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    private final VideoRepository repository;

    public VideoService(VideoRepository repository) {
        this.repository = repository;
    }

    public List<Video> findAll() {
        return repository.findAll();
    }

    public List<Video> search(VideoSearchRequest request) {
        QVideo v = QVideo.video;
        BooleanBuilder builder = new BooleanBuilder();

        if (request.title() != null && !request.title().isBlank()) {
            builder.and(v.title.containsIgnoreCase(request.title().trim()));
        }

        if (request.category() != null && !request.category().isBlank()) {
            builder.and(v.category.equalsIgnoreCase(request.category().trim()));
        }

        if (request.genre() != null && !request.genre().isBlank()) {
            builder.and(v.genre.containsIgnoreCase(request.genre().trim()));
        }

        if (request.language() != null && !request.language().isBlank()) {
            builder.and(v.language.equalsIgnoreCase(request.language().trim()));
        }

        if (request.maturityRating() != null && !request.maturityRating().isBlank()) {
            builder.and(v.maturityRating.equalsIgnoreCase(request.maturityRating().trim()));
        }

        if (request.minImdbRating() != null) {
            builder.and(v.imdbRating.goe(request.minImdbRating()));
        }

        if (request.maxImdbRating() != null) {
            builder.and(v.imdbRating.loe(request.maxImdbRating()));
        }

        if (request.releaseYearFrom() != null) {
            builder.and(v.releaseYear.goe(request.releaseYearFrom()));
        }

        if (request.releaseYearTo() != null) {
            builder.and(v.releaseYear.loe(request.releaseYearTo()));
        }

        Iterable<Video> iterable = repository.findAll(builder);

        List<Video> result = new ArrayList<>();
        iterable.forEach(result::add);
        return result;
    }

    public Video findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Video not found with id: " + id));
    }
}