package com.youssef.gamal.mqs.mq_examples.consumers;

import com.youssef.gamal.mqs.mq_examples.dtos.NotificationDto;

public interface NotificationMQListenerServiceIfc {

    void processNotification(NotificationDto notificationDto);
    
}
