package com.trong.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${rabbit.config.topicExchangeName}")
    private String topicExchangeName;

    @Value("${rabbit.config.queueName}")
    private String queueName;

    @Value("${rabbit.config.routingKey}")
    private String routingKey;


    @Value("${rabbit.config.topicExchangeNameJSON}")
    private String topicExchangeNameJSON;

    @Value("${rabbit.config.routingKeyJSON}")
    private String routingKeyJSON;

    @Value("${rabbit.config.queueNameJSON}")
    private String queueNameJSON;

    @Bean
    public Queue queue(){
        return new Queue(queueName);
    }

    @Bean
    public Queue queueJSON(){
        return new Queue(queueNameJSON);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    public TopicExchange topicExchangeJSON(){
        return new TopicExchange(topicExchangeNameJSON);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(topicExchange()).with(routingKey);
    }



    @Bean
    public Binding bindingJSON(){
        return BindingBuilder.bind(queueJSON())
                .to(topicExchangeJSON()).with(routingKeyJSON);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
