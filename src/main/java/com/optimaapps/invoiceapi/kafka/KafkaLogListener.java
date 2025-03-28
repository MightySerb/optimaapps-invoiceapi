package com.optimaapps.invoiceapi.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaLogListener {

    @KafkaListener(topics = "http-logs", groupId = "log-group")
    public void listen(String logMessage) {
    }
}
