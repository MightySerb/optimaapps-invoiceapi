package com.optimaapps.invoiceapi.repository;

import com.optimaapps.invoiceapi.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
