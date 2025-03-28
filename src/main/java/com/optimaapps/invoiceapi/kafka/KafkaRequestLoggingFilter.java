package com.optimaapps.invoiceapi.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaRequestLoggingFilter extends OncePerRequestFilter {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaRequestLoggingFilter(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            RequestLog log = new RequestLog();
            log.setMethod(request.getMethod());
            log.setUri(request.getRequestURI());
            log.setHeaders(extractHeaders(request));
            log.setRequestBody(new String(wrappedRequest.getContentAsByteArray(), request.getCharacterEncoding()));
            log.setStatus(response.getStatus());
            log.setResponseBody(new String(wrappedResponse.getContentAsByteArray(), response.getCharacterEncoding()));
            log.setTimestamp(LocalDateTime.now());
            kafkaTemplate.send("http-logs", log.toString());
            wrappedResponse.copyBodyToResponse();
        }
    }

    private Map<String, String> extractHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            headers.put(header, request.getHeader(header));
        }
        return headers;
    }
}