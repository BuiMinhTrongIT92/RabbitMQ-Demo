package com.trong.rabbitmq.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trong.rabbitmq.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitProducer {

    @Value("${rabbit.config.topicExchangeName}")
    private String topicExchangeName;

    @Value("${rabbit.config.routingKey}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RabbitProducer.class);

    public RabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    //Using send message from Producer
    public void sendMessage(String message){
        logger.info(String.format("Message sent -> %s", message));
        rabbitTemplate.convertAndSend(topicExchangeName,routingKey, message);
    }
}
