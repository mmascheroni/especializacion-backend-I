package com.dh.catalogservice.repository;

import com.dh.catalogservice.model.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRepository extends MongoRepository<Catalog, String> {

    List<Catalog> findAllByGenre(String genre);
}
