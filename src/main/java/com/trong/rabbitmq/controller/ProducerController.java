package com.trong.rabbitmq.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trong.rabbitmq.model.User;
import com.trong.rabbitmq.publisher.RabbitJSONProducer;
import com.trong.rabbitmq.publisher.RabbitProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/rabbitmq/")
public class ProducerController {

    @Autowired
    private RabbitProducer rabbitProducer;

    @Autowired
    private RabbitJSONProducer rabbitJSONProducer;

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        rabbitProducer.sendMessage(message);
        return new ResponseEntity<>(String.format("Message send %s", message), HttpStatus.OK);
    }

    @PostMapping("/publish-json")
    public ResponseEntity<String> sendMessageJSON(@RequestBody User user) throws JsonProcessingException {
        rabbitJSONProducer.sendMessageJSON(user);
        ObjectMapper obj = new ObjectMapper();
        return new ResponseEntity<>(String.format("Message send %s", obj.writeValueAsString(user)), HttpStatus.OK);
    }
}
