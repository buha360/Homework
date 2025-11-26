package com.buha360.MovieSearcher;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
        Specification<Video> spec = (root, query, cb) -> cb.conjunction();

        if (request.title() != null && !request.title().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("title")), "%" + request.title().toLowerCase() + "%")
            );
        }

        if (request.category() != null && !request.category().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(cb.lower(root.get("category")), request.category().toLowerCase())
            );
        }

        if (request.genre() != null && !request.genre().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(cb.lower(root.get("genre")), request.genre().toLowerCase())
            );
        }

        if (request.language() != null && !request.language().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(cb.lower(root.get("language")), request.language().toLowerCase())
            );
        }

        if (request.maturityRating() != null && !request.maturityRating().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(cb.lower(root.get("maturityRating")), request.maturityRating().toLowerCase())
            );
        }

        if (request.minImdbRating() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("imdbRating"), request.minImdbRating())
            );
        }

        if (request.maxImdbRating() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("imdbRating"), request.maxImdbRating())
            );
        }

        if (request.releaseYearFrom() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("releaseYear"), request.releaseYearFrom())
            );
        }

        if (request.releaseYearTo() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("releaseYear"), request.releaseYearTo())
            );
        }

        return repository.findAll(spec);
    }

    public Video findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Video not found with id: " + id));
    }
}