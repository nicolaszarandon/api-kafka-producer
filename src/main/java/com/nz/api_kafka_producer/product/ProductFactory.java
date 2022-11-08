package com.nz.api_kafka_producer.product;
import org.springframework.stereotype.Component;

@Component
public class ProductFactory {
    public Product createProduct(int indice){

        return Product.builder()
                .name("nombre")
                .count(1L)
                .color("color")
                .id(Long.valueOf(indice))
                .build();
    }
}
