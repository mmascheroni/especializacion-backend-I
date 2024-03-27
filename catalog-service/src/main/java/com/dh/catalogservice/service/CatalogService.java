package com.dh.catalogservice.service;

import com.dh.catalogservice.model.Catalog;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    public Catalog saveMovieCatalog(Movie movie) {
        Catalog catalog = new Catalog(movie);

        return catalogRepository.save(catalog);
    }

    public Catalog saveSerieCatalog(Serie serie) {
        Catalog catalog = new Catalog(serie);

        return catalogRepository.save(catalog);
    }

    public List<Catalog> getAllCatalog() {
        return catalogRepository.findAll();
    }

    public List<Catalog> getAllCatalogByGenre(String genre) {
        return catalogRepository.findAllByGenre(genre);
    }

}
