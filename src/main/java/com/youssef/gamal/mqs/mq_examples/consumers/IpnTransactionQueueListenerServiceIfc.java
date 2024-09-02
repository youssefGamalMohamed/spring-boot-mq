package com.youssef.gamal.mqs.mq_examples.consumers;

import org.springframework.amqp.core.Message;

public interface IpnTransactionQueueListenerServiceIfc {

    void handleIpnTransactionMessage(Message message);
    
}
