package com.optimaapps.invoiceapi.kafka;

import lombok.Data;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Data
public class RequestLog {
    private String method;
    private String uri;
    private Map<String, String> headers;
    private String requestBody;
    private int status;
    private String responseBody;
    private LocalDateTime timestamp;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String toString() {
        String formattedRequestBody = requestBody.replaceAll("\\r\\n|\\r|\\n", " ").replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
        return String.format("\n New Request: Timestamp: %s | Method: %s | URI: %s | Status: %d | RequestBody: %s | ResponseBody: %s",
                timestamp.format(formatter), method, uri, status, formattedRequestBody, responseBody);
    }
}