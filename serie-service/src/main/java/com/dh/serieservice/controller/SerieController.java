package com.dh.serieservice.controller;

import com.dh.serieservice.model.Serie;
import com.dh.serieservice.queue.SerieSender;
import com.dh.serieservice.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    private final SerieService serieService;

    @Autowired
    private SerieSender serieSender;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping()
    public List<Serie> getAll(@RequestParam(defaultValue = "false") Boolean throwError) {
        return serieService.getAll(throwError);
    }

    @GetMapping("/{genre}")
    public List<Serie> getSerieByGenre(@PathVariable String genre) {
        return serieService.getSeriesBygGenre(genre);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Serie serie) {
        serieSender.sendSerie(serie);
        serieService.create(serie);
        return serie.getId();
    }
}
