package com.youssef.gamal.mqs.mq_examples.consumers;

import com.youssef.gamal.mqs.mq_examples.dtos.OrderDto;

public interface InventoryMQListenerServiceIfc {

    void processOrder(OrderDto orderDto);
    
}
