package com.dh.catalogservice.queue;

import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.service.CatalogService;
import com.dh.catalogservice.service.MovieService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CatalogListener {

    @Autowired
    private CatalogService catalogService;

    @RabbitListener(queues = {"${queue.movie.name}"})
    public void receiveMovie(@Payload Movie movie) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        catalogService.saveMovieCatalog(movie);
    }

    @RabbitListener(queues = {"${queue.serie.name}"})
    public void receiveSerie(@Payload Serie serie) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        catalogService.saveSerieCatalog(serie);
    }
}
