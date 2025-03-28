package com.optimaapps.invoiceapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableJpaAuditing
@EnableKafka
public class InvoiceapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceapiApplication.class, args);
	}

}
