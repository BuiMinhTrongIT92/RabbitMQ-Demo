package com.trong.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitConsumer.class);

//    @RabbitListener(queues = {"${rabbit.config.queueName}"})
//    public void consume(String message) {
//        logger.info(String.format("Received message -> %s", message));
//    }
}
