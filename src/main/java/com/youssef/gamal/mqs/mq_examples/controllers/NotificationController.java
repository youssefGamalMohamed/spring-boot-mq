package com.youssef.gamal.mqs.mq_examples.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.youssef.gamal.mqs.mq_examples.dtos.NotificationDto;
import com.youssef.gamal.mqs.mq_examples.producers.NotificationServiceInterface;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@Slf4j
public class NotificationController {


    @Autowired
    private NotificationServiceInterface notificationServiceInterface;

    @PostMapping("/api/v1/notifications")
    public ResponseEntity<?> pushNoticiation(@RequestBody NotificationDto notificationDto) {
        log.info("pushNoticiation({}): " + notificationDto);  
        notificationServiceInterface.pushNotification(notificationDto);
        return ResponseEntity.ok()
            .body(Map.of("message" , "done"));
    }
}
