package com.youssef.gamal.mqs.mq_examples.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.youssef.gamal.mqs.mq_examples.dtos.NotificationDto;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class NotificationRabbitMQServiceImpl implements NotificationServiceInterface {

    @Value("${rabbitmq.exchange-names.notification_topic_exchange}")
    private String notification_topic_exchange;

    @Value("${rabbitmq.routing-keys.notification_service_routing_default_key}")
    private String notification_service_routing_default_key;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public boolean pushNotification(NotificationDto notificationDto) {
        log.info("pushNotification({}): --> we will send message = " + notificationDto);
        // here we define the default key to public the msg to all connected queues related to the same 
        // topic with the same key so all queues with the same key will recieve the same message
        rabbitTemplate.convertAndSend(notification_topic_exchange, notification_service_routing_default_key, notificationDto);
        log.info("pushNotification(): --> message Sent Successfully");
        return true;
    }

}
