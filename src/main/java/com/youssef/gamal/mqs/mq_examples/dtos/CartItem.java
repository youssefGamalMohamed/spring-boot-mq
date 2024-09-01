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
public class CartItem implements Serializable {

    private ProductDto product;
    private Integer orderedQuantityFromProduct;

}
