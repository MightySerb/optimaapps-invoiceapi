package com.optimaapps.invoiceapi.controller;

import com.optimaapps.invoiceapi.dto.InvoiceInsertRequestDTO;
import com.optimaapps.invoiceapi.dto.InvoiceUpdateRequestDTO;
import com.optimaapps.invoiceapi.entity.Invoice;
import com.optimaapps.invoiceapi.exception.InvoiceNotFoundException;
import com.optimaapps.invoiceapi.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody InvoiceInsertRequestDTO invoiceInsertRequestDTO) {
        Invoice createdInvoice = invoiceService.createInvoice(invoiceInsertRequestDTO);
        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        Invoice invoice = invoiceService.getInvoiceById(id).orElseThrow(() -> new InvoiceNotFoundException("Invoice sa id-jem " + id + " nije pronadjen"));
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Invoice> updateInvoice(@Valid @RequestBody InvoiceUpdateRequestDTO invoiceUpdateRequestDTO) {
        Invoice updatedInvoice = invoiceService.updateInvoice(invoiceUpdateRequestDTO);
        return new ResponseEntity<>(updatedInvoice, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Long>> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        Map<String, Long> response = new HashMap<>();
        response.put("deletedId", id);
        return ResponseEntity.ok(response);
    }
}
