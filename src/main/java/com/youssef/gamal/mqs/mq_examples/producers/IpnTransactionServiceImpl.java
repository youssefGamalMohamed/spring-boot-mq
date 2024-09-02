package com.youssef.gamal.mqs.mq_examples.producers;

import java.util.UUID;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String makeTransaction(IpnTransactionDto ipnTransactionDto) {
        log.info("makeTransaction({}): --> we will send message = " + ipnTransactionDto);
        String correlationId = UUID.randomUUID().toString();
        Message message = null;
        try {
            message = MessageBuilder.withBody(objectMapper.writeValueAsString(ipnTransactionDto).getBytes())
                .setCorrelationId(correlationId)
                .build();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        rabbitTemplate.convertAndSend(ipnExchange, ipn_transaction_queue_routingKey, message);
        log.info("makeTransaction(): --> message Sent Successfully");
        return correlationId;
    }

  

}
