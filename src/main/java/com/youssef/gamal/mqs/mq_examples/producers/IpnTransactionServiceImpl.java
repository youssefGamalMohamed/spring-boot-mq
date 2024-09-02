package com.youssef.gamal.mqs.mq_examples.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.youssef.gamal.mqs.mq_examples.dtos.IpnTransactionDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IpnTransactionServiceImpl implements IpnTransactionServiceIfc {
    
    
    @Value("${rabbitmq.exchange-names.ipn_exchange}")
    private String ipnExchange;

    @Value("${rabbitmq.routing-keys.ipn_transaction_queue_routingKey}")
    private String ipn_transaction_queue_routingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public boolean makeTransaction(IpnTransactionDto ipnTransactionDto) {
        log.info("makeTransaction({}): --> we will send message = " + ipnTransactionDto);
        // here we define the default key to public the msg to all connected queues related to the same 
        // topic with the same key so all queues with the same key will recieve the same message
        rabbitTemplate.convertAndSend(ipnExchange, ipn_transaction_queue_routingKey, ipnTransactionDto);
        log.info("makeTransaction(): --> message Sent Successfully");
        return true;
    }

}
