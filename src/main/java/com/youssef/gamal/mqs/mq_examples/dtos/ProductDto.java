package com.youssef.gamal.mqs.mq_examples.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {

    private String name;
    private Integer quantity;
    private Double price;

}
