package com.dh.catalogservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "Catalog")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
public class Catalog {

    @Id
    private String id;

    private String genre;
    private Movie movie;
    private Serie serie;

    public Catalog() {
    }

    public Catalog(Movie movie) {
        this.id = UUID.randomUUID().toString();
        this.movie = movie;
        this.genre = movie.getGenre();
    }

    public Catalog(Serie serie) {
        this.id = UUID.randomUUID().toString();
        this.serie = serie;
        this.genre = serie.getGenre();
    }

}

