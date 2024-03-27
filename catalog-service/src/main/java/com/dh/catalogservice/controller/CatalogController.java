package com.dh.catalogservice.controller;

import com.dh.catalogservice.feign.IMovieClient;
import com.dh.catalogservice.model.Catalog;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.service.CatalogService;
import com.dh.catalogservice.service.MovieService;
import com.dh.catalogservice.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private SerieService serieService;

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/movies/{genre}")
    public List<Movie> getMoviesByGenre(@PathVariable String genre) {
        return movieService.getCatalogByGenre(genre);
    }

    @PostMapping("/movies/save")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @GetMapping("/movies")
    public String getRandomId() {
        return movieService.getRandomId();
    }

    @GetMapping("/series")
    public List<Serie> getAll() {
        return serieService.getAll();
    }

    @GetMapping("/series/{genre}")
    public List<Serie> getSeriesByGenre(@PathVariable String genre) {
        return serieService.getSeriesByGenre(genre);
    }

    @PostMapping("/series/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Serie serie) {
        return serieService.create(serie);
    }

    @GetMapping()
    public List<Catalog> getCatalog() {
        return catalogService.getAllCatalog();
    }

    @GetMapping("/{genre}")
    public List<Catalog> getCatalogByGenre(@PathVariable String genre) {
        return catalogService.getAllCatalogByGenre(genre);
    }

}
