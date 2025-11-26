package com.buha360.MovieSearcher;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin(origins = "http://localhost:4200")
public class VideoController {

    private final VideoService service;

    public VideoController(VideoService service) {
        this.service = service;
    }

    // 1) Összes videó (pl. kezdő betöltéshez)
    @GetMapping("/all")
    public List<Video> getAllVideos() {
        return service.findAll();
    }

    // 2) Egy konkrét videó ID alapján (detail view-hoz)
    @GetMapping("/{id}")
    public Video getVideoById(@PathVariable Long id) {
        return service.findById(id);
    }

    // 3) Keresés query paraméterekkel
    @GetMapping
    public List<Video> searchVideos(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String maturityRating,
            @RequestParam(required = false) Double minImdbRating,
            @RequestParam(required = false) Double maxImdbRating,
            @RequestParam(required = false) Integer releaseYearFrom,
            @RequestParam(required = false) Integer releaseYearTo
    ) {
        var request = new VideoSearchRequest(
                title,
                category,
                genre,
                language,
                maturityRating,
                minImdbRating,
                maxImdbRating,
                releaseYearFrom,
                releaseYearTo
        );

        return service.search(request);
    }
}