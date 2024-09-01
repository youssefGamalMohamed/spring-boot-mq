package com.youssef.gamal.mqs.mq_examples.dtos;

import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {

    private Set<CartItem> cartItems;

}
