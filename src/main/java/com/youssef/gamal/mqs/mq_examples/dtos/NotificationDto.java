package com.youssef.gamal.mqs.mq_examples.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class NotificationDto implements Serializable {
    private String title;
    private String content;
}
