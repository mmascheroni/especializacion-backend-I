package com.dh.catalogservice.feign;

import com.dh.catalogservice.model.Serie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "serie-service")
public interface ISerieClient {

    @GetMapping("/series")
    List<Serie> getAll(@RequestParam Boolean throwError);

    @GetMapping("/series/{genre}")
    List<Serie> getSerieByGenre(@PathVariable String genre);

    @PostMapping("/series/save")
    String create(@RequestBody Serie serie);
}
