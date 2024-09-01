package com.youssef.gamal.mqs.mq_examples.producers;

import com.youssef.gamal.mqs.mq_examples.dtos.NotificationDto;

public interface NotificationServiceInterface {

    boolean pushNotification(NotificationDto notificationDto);
    
}
