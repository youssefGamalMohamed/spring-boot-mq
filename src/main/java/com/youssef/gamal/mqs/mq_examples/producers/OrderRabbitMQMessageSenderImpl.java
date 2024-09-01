package com.youssef.gamal.mqs.mq_examples.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class OrderRabbitMQMessageSenderImpl implements OrderMessageSenderInterface {

    @Value("${rabbitmq.exchange-names.ecommerce_exchange}")
    private String ecommerce_exchange;

    @Value("${rabbitmq.routing-keys.order_inventory_service_routing_key}")
    private String order_inventory_service_routing_key;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public String sendMessage(Object msg) {
       log.info("sendMessage(): --> we will send message = " + msg);
       rabbitTemplate.convertAndSend(ecommerce_exchange, order_inventory_service_routing_key, msg);
       log.info("sendMessage(): --> message Sent Successfully");
       return "Success";
    }

}
