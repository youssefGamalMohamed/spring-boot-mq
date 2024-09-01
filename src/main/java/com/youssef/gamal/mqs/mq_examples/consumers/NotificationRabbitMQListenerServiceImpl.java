package com.youssef.gamal.mqs.mq_examples.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.youssef.gamal.mqs.mq_examples.dtos.NotificationDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationRabbitMQListenerServiceImpl implements NotificationMQListenerServiceIfc {

    @RabbitListener(queues = {"${rabbitmq.queues-names.notification_system_user1_queue}",
    "${rabbitmq.queues-names.notification_system_user2_queue}"})
    @Override
    public void processNotification(NotificationDto notificationDto) {
        log.info("processNotification(): msg pulled from queue = "+ notificationDto.toString());
    }

}
