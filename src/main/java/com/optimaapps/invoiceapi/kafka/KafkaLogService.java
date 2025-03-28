package com.optimaapps.invoiceapi.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaLogService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaLogService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void logToKafka(String logMessage) {
        kafkaTemplate.send("http-logs", logMessage);
    }
}
