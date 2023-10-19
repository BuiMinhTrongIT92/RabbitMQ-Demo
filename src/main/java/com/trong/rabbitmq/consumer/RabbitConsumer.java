package com.trong.rabbitmq.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RabbitConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitConsumer.class);

    @RabbitListener(queues = { "${rabbit.config.queueName}" })
    public void consume(String message) {
        logger.info(String.format("Received message -> %s", message));
        System.out.println("22");
    }

    @RabbitListener(queues = { "${rabbit.config.queueNameJSON}" })
    public void consume(Map<String, Object> user) throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        logger.info(String.format("Received message JSON -> %s", obj.writeValueAsString(user)));

        System.out.println("ok");
    }
}
