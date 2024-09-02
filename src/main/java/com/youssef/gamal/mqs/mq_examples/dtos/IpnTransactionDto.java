package com.youssef.gamal.mqs.mq_examples.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class IpnTransactionDto implements Serializable {
    
    private String fromBankUser;
    private String toBankUser;
    private Double amount;

}
