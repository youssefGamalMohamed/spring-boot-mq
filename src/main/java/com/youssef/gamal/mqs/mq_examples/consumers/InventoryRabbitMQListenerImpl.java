package com.youssef.gamal.mqs.mq_examples.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.youssef.gamal.mqs.mq_examples.dtos.OrderDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventoryRabbitMQListenerImpl implements InventoryMQListenerServiceIfc {


    @RabbitListener(queues = {"${rabbitmq.queues-names.order_inventory_service_queue}"})
    @Override
    public void processOrder(OrderDto orderDto) {
        log.info("processOrder(): msg pulled from queue = "+ orderDto.toString());
    } 
}
