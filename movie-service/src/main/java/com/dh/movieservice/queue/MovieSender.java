package com.dh.movieservice.queue;

import com.dh.movieservice.models.Movie;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue movieQueue;

    public void sendMovie(Movie movie) {
        this.rabbitTemplate.convertAndSend(this.movieQueue.getName(), movie);
    }
}
