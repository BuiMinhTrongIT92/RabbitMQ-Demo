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
public class RabbitJSONProducer {

    @Value("${rabbit.config.topicExchangeNameJSON}")
    private String topicExchangeNameJSON;

    @Value("${rabbit.config.routingKeyJSON}")
    private String routingKeyJSON;

    private RabbitTemplate rabbitTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RabbitProducer.class);

    public RabbitJSONProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    //Using send message JSON data from Producer
    public void sendMessageJSON(User user) throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        logger.info(String.format("Message sent -> %s", obj.writeValueAsString(user)));
        rabbitTemplate.convertAndSend(topicExchangeNameJSON,routingKeyJSON, user);
    }
}
