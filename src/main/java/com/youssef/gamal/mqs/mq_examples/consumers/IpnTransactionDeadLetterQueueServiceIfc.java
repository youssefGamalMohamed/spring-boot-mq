package com.youssef.gamal.mqs.mq_examples.consumers;


import com.youssef.gamal.mqs.mq_examples.dtos.IpnTransactionDto;

public interface IpnTransactionDeadLetterQueueServiceIfc {

    void processMessageFromDeadLetterQueue(IpnTransactionDto ipnTransactionDto);
}
