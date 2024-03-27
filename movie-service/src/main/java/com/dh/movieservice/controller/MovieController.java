package com.dh.movieservice.controller;

import com.dh.movieservice.models.Movie;
import com.dh.movieservice.queue.MovieSender;
import com.dh.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieSender movieSender;

    @Value("${randomId}")
    private String randomId;

    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre) {
        return ResponseEntity.ok().body(movieService.findByGenre(genre));
    }

    @PostMapping("/save")
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        movieSender.sendMovie(movie);
        return ResponseEntity.ok().body(movieService.save(movie));
    }

    @GetMapping()
    String getRandomId() {
        return randomId;
    }
}
