package com.nz.api_kafka_producer.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
    private Long id;
    private String name;
    private String color;
    private Long count;
}
