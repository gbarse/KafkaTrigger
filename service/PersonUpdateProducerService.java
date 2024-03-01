package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.apache.kafka.common.requests.DeleteAclsResponse.log;

@Service
public class PersonUpdateProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void triggerRandomPersonUpdate(String topic, UUID personId) {
        try {
            kafkaTemplate.send(topic, personId.toString());
        } catch (Exception e) {
            log.error("Error sending random update message", e);
        }
    }

}