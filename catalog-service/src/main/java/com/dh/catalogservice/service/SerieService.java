package com.dh.catalogservice.service;

import com.dh.catalogservice.feign.ISerieClient;
import com.dh.catalogservice.model.Serie;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SerieService {

    @Autowired
    private ISerieClient iSerieClient;

    @CircuitBreaker(name = "serie", fallbackMethod = "serieEmptyListFallbackMethod")
    @Retry(name = "serie")
    public List<Serie> getAll() {
        log.info("Calling serie service ...");
        return iSerieClient.getAll(false);
    }

    @CircuitBreaker(name = "serie", fallbackMethod = "serieEmptyListFallbackMethod")
    @Retry(name = "serie")
    public List<Serie> getSeriesByGenre(String genre) {
        return iSerieClient.getSerieByGenre(genre);
    }

    public String create(Serie serie) {
        return iSerieClient.create(serie);
    }

    private List<Serie> serieEmptyListFallbackMethod(CallNotPermittedException e) {
        return new ArrayList<>();
    }

}
