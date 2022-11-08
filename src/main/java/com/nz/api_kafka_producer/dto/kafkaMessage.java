package com.nz.api_kafka_producer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class kafkaMessage<T>{
    public String action;
    public T data;
}
