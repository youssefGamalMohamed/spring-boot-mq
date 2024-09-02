package com.youssef.gamal.mqs.mq_examples.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.youssef.gamal.mqs.mq_examples.dtos.IpnTransactionDto;
import com.youssef.gamal.mqs.mq_examples.producers.IpnTransactionServiceIfc;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@Slf4j
public class IpnTransactionsController {
    


    @Autowired
    private IpnTransactionServiceIfc ipnTransactionServiceIfc;

    @PostMapping("/api/v1/transactions")
    public ResponseEntity<?> addTransaction(@RequestBody IpnTransactionDto ipnTransactionDto) {
        log.info("addTransaction({})",ipnTransactionDto);
        String correlationId = ipnTransactionServiceIfc.makeTransaction(ipnTransactionDto);
        return ResponseEntity.ok()
                        .body(Map.of("corrleationId", correlationId));
    }

}
