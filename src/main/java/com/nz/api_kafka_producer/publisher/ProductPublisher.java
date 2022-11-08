package com.nz.api_kafka_producer.publisher;

import com.nz.api_kafka_producer.dto.kafkaMessage;
import com.nz.api_kafka_producer.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class ProductPublisher {
    @Autowired
    private KafkaTemplate<String, kafkaMessage> producer;
    private Logger log = LoggerFactory.getLogger(ProductPublisher.class);
    private String topic = "products.v1.created";

    public void send(String key , Product product) {
        // asynchronous send: faster
        producer.send(topic, key, new kafkaMessage("Created", product));        // synchronous send
        // producer.send(topic, key,String.valueOf(new kafkaMessage("created", product))).get();
         log.trace(String.valueOf(product));
    }

    public void sendWithCallbacks(String key, Product product) {
       ListenableFuture<SendResult<String, kafkaMessage>> future  = producer.send(topic, key, new kafkaMessage("created", product));
       future.addCallback(new KafkaSendCallback<String, kafkaMessage>(){

            @Override
            public void onSuccess(SendResult<String, kafkaMessage> result) {
               log.error("Error sending message ", result);
            }

            @Override
           public void onFailure(KafkaProducerException ex) {
               log.error("Error sending message ", ex);
            }
            @Override
            public void onFailure(Throwable ex) {
                log.error("Error sending message ", ex);
            }
        });
    }
}
