package com.youssef.gamal.mqs.mq_examples.producers;

import com.youssef.gamal.mqs.mq_examples.dtos.IpnTransactionDto;

public interface IpnTransactionServiceIfc {

    String makeTransaction(IpnTransactionDto ipnTransactionDto);
    
}
