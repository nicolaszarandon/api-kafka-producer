package com.nz.api_kafka_producer.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nz.api_kafka_producer.dto.kafkaMessage;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;


public class KafkaProducerConfig {


    //private final ObjectMapper objectMapper;

    public Map<String, Object> producerProps(KafkaProperties kafkaProperties) {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        // PROPIEDADES ADICIONALES
        // Define los reintentos que se realizarán en caso de error.
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        // El producer agrupará los registros en batches, mejorando el performance (está definido en bytes).
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        // Los batches se agruparan de acuerdo de un periodo de tiempo, está definido en milisegundos
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        // Define el espacio de memoria que se asignará para colocar los mensajes que están pendientes por enviar
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(JsonSerializer.TYPE_MAPPINGS,"com.nz.api_kafka_producer.dto.kafkaMessage");

        return props;
    }

    @Bean
    public ProducerFactory<String, kafkaMessage> producerFactory(KafkaProperties kafkaProperties) {
        return new DefaultKafkaProducerFactory(producerProps(kafkaProperties));
    }

    @Bean
    public KafkaTemplate<String, kafkaMessage> createTemplate(KafkaProperties kafkaProperties) {
        Map<String, Object> senderProps = producerProps(kafkaProperties);
        ProducerFactory<String, kafkaMessage> producerFactory = new DefaultKafkaProducerFactory<>(senderProps);
        KafkaTemplate<String, kafkaMessage> template = new KafkaTemplate<>(producerFactory);
        return template;
    }


}