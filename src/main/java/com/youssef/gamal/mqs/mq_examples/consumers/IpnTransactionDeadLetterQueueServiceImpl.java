package com.youssef.gamal.mqs.mq_examples.consumers;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.youssef.gamal.mqs.mq_examples.dtos.IpnTransactionDto;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class IpnTransactionDeadLetterQueueServiceImpl implements IpnTransactionDeadLetterQueueServiceIfc {
    
    @RabbitListener(queues = {"${rabbitmq.queues-names.ipn_transaction_deadLetter_queue}"})
    @Override
    public void processMessageFromDeadLetterQueue(IpnTransactionDto ipnTransactionDto) {
        log.info("processMessageFromDeadLetterQueue({})",ipnTransactionDto);
    }

}
