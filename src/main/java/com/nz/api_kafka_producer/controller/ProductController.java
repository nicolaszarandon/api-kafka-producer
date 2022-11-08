package com.nz.api_kafka_producer.controller;

import com.nz.api_kafka_producer.product.Product;
import com.nz.api_kafka_producer.product.ProductFactory;
import com.nz.api_kafka_producer.publisher.ProductPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/products")
public class ProductController {

    @Autowired
    private ProductFactory productFactory;
    @Autowired
    private ProductPublisher productPublisher;

    private Logger log = LoggerFactory.getLogger(ProductController.class);


    @GetMapping("/")
    public void show() {

        for (int i=0;i<10;i++){

         Product product= productFactory.createProduct(i);
         String key = "key_even";
         productPublisher.send(key, product);
        }

        log.info("Se han enviado eventos de productos creado");
    }

}
