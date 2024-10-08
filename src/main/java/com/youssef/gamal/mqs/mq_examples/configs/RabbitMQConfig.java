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

    @Value("${rabbitmq.exchange-names.ipn_exchange}")
    private String ipnExchange;

    @Value("${rabbitmq.routing-keys.order_inventory_service_routing_key}")
    private String order_inventory_service_routing_key;

    @Value("${rabbitmq.queues-names.notification_system_user1_queue}")
    private String notification_system_user1_queue;

    @Value("${rabbitmq.queues-names.notification_system_user2_queue}")
    private String notification_system_user2_queue;

    @Value("${rabbitmq.queues-names.ipn_transaction_queue}")
    private String ipnTransactionQueue;

    @Value("${rabbitmq.queues-names.ipn_transaction_deadLetter_queue}")
    private String ipnTransactionDeadLetterQueue;

    @Value("${rabbitmq.routing-keys.notification_service_routing_default_key}")
    private String notification_service_routing_default_key;

    @Value("${rabbitmq.routing-keys.ipn_transaction_queue_routingKey}")
    private String ipn_transaction_queue_routingKey;

    @Value("${rabbitmq.routing-keys.ipn_deadLetter_queue_routingKey}")
    private String ipn_deadLetter_queue_routingKey;


    @Bean
    public Queue ipnTransactionQueue() {
        Queue ipnQ =  new Queue(this.ipnTransactionQueue);
        ipnQ.addArgument("x-message-ttl", 360000);
        ipnQ.addArgument("x-dead-letter-exchange", this.ipnExchange);
        ipnQ.addArgument("x-dead-letter-routing-key", this.ipn_deadLetter_queue_routingKey);
        return ipnQ;
    }

    @Bean
    public Queue ipnTransactionDeadLetterQueue() {
        return new Queue(this.ipnTransactionDeadLetterQueue);
    }

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
    public DirectExchange ipnExchangeBean() {
        return new DirectExchange(this.ipnExchange);
    }

    

    @Bean
    public TopicExchange noificationSystemTopicExchange() {
        return new TopicExchange(this.notification_topic_exchange);
    }

    @Bean
    public Binding binding_ipnTransactionQueue_to_ipnExchange() {
        return BindingBuilder.bind(ipnTransactionQueue())
                .to(ipnExchangeBean())
                .with(this.ipn_transaction_queue_routingKey);
    }

    @Bean
    public Binding binding_ipnTransactionDeadLetterQueue_to_ipnExchange() {
        return BindingBuilder.bind(ipnTransactionDeadLetterQueue())
                .to(ipnExchangeBean())
                .with(this.ipn_deadLetter_queue_routingKey);
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
