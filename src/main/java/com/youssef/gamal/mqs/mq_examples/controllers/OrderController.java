package com.youssef.gamal.mqs.mq_examples.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.youssef.gamal.mqs.mq_examples.dtos.OrderDto;
import com.youssef.gamal.mqs.mq_examples.producers.OrderMessageSenderInterface;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@Slf4j
public class OrderController {


    @Autowired
    private OrderMessageSenderInterface messageSenderInterface;

    @PostMapping("/api/v1/orders")
    public ResponseEntity<?> makeOrder(@RequestBody OrderDto orderDto) {
        log.info("makeOrder(): " + orderDto);  
        messageSenderInterface.sendMessage(orderDto);
        return ResponseEntity.ok()
            .body(Map.of("message" , "done"));
    }
}
