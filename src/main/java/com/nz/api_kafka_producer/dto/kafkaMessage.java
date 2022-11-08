package com.nz.api_kafka_producer.dto;

import com.nz.api_kafka_producer.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class kafkaMessage{
    public String action;
    public Product data;
}
