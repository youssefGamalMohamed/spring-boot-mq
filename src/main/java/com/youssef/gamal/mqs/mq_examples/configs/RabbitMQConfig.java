package com.youssef.gamal.mqs.mq_examples.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {


    @Value("${rabbitmq.queues-names.order_inventory_service_queue}")
    private String order_inventory_service_queue;


    @Value("${rabbitmq.exchange-names.ecommerce_exchange}")
    private String ecommerce_exchange;

    @Value("${rabbitmq.exchange-names.notification_topic_exchange}")
    private String notification_topic_exchange;

    @Value("${rabbitmq.routing-keys.order_inventory_service_routing_key}")
    private String order_inventory_service_routing_key;

    @Value("${rabbitmq.queues-names.notification_system_user1_queue}")
    private String notification_system_user1_queue;

    @Value("${rabbitmq.queues-names.notification_system_user2_queue}")
    private String notification_system_user2_queue;


    @Value("${rabbitmq.routing-keys.notification_service_routing_default_key}")
    private String notification_service_routing_default_key;

    @Bean
    public Queue notificationSystemUser1Queue() {
        return new Queue(this.notification_system_user1_queue);
    }



    @Bean
    public Queue notificationSystemUser2Queue() {
        return new Queue(this.notification_system_user2_queue);
    }



    @Bean
    public Queue orderInventoryQueue() {
        return new Queue(this.order_inventory_service_queue);
    }


    @Bean
    public DirectExchange ecommerceExchange() {
        return new DirectExchange(this.ecommerce_exchange);
    }


    @Bean
    public TopicExchange noificationSystemTopicExchange() {
        return new TopicExchange(this.notification_topic_exchange);
    }

    @Bean
    public Binding binding_orderInventoryQueue_to_ecommerceExchange() {
        return BindingBuilder.bind(orderInventoryQueue())
                .to(ecommerceExchange())
                .with(this.order_inventory_service_routing_key);
    }

    
    @Bean
    public Binding binding_notificationUser1Queue_to_notificationTopicExchange() {
        return BindingBuilder.bind(notificationSystemUser1Queue())
                .to(noificationSystemTopicExchange())
                .with(this.notification_service_routing_default_key);
    }

    @Bean
    public Binding binding_notificationUser2Queue_to_notificationTopicExchange() {
        return BindingBuilder.bind(notificationSystemUser2Queue())
                .to(noificationSystemTopicExchange())
                .with(this.notification_service_routing_default_key);
    }

    @Bean
    public MessageConverter jackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(RabbitTemplate rabbitTemplate) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setMessageConverter(jackson2MessageConverter());
        factory.setConnectionFactory(rabbitTemplate.getConnectionFactory());
        return factory;
    }
}
