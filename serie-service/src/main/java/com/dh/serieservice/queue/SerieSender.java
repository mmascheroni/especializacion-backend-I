package com.dh.serieservice.queue;

import com.dh.serieservice.model.Serie;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SerieSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue serieQueue;

    public void sendSerie(Serie serie) {
        this.rabbitTemplate.convertAndSend(this.serieQueue.getName(), serie);
    }
}
