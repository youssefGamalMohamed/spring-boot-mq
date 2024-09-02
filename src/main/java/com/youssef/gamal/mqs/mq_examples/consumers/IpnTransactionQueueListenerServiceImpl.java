package com.youssef.gamal.mqs.mq_examples.consumers;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IpnTransactionQueueListenerServiceImpl implements IpnTransactionQueueListenerServiceIfc {

    @RabbitListener(queues = {"${rabbitmq.queues-names.ipn_transaction_queue}"})
    @Override
    public void handleIpnTransactionMessage(Message message) {
        log.info("handleIpnTransactionMessage({})", new String(message.getBody()));
        log.info("handleIpnTransactionMessage() : correlationId = "+ message.getMessageProperties()
            .getCorrelationId());
    }

}
