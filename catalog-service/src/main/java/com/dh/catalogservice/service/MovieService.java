package com.dh.catalogservice.service;

import com.dh.catalogservice.feign.IMovieClient;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private IMovieClient iMovieClient;

    @CircuitBreaker(name = "movie", fallbackMethod = "emptyListFallbackMethod")
    @Retry(name = "movie")
    public ResponseEntity<List<Movie>> getCatalogByGenre(@PathVariable String genre) {
        return iMovieClient.getMovieByGenre(genre);
    }

    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return iMovieClient.saveMovie(movie);
    }

    public String getRandomId() {
        return iMovieClient.getRandomId();
    }

    private List<Serie> emptyListFallbackMethod(CallNotPermittedException e) {
        return new ArrayList<>();
    }
}
