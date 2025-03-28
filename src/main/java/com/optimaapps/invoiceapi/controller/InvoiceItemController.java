package com.optimaapps.invoiceapi.controller;

import com.optimaapps.invoiceapi.dto.InvoiceItemInsertRequestDTO;
import com.optimaapps.invoiceapi.dto.InvoiceItemUpdateRequestDTO;
import com.optimaapps.invoiceapi.entity.Invoice;
import com.optimaapps.invoiceapi.entity.InvoiceItem;
import com.optimaapps.invoiceapi.exception.InvoiceNotFoundException;
import com.optimaapps.invoiceapi.service.InvoiceItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/invoice-items")
public class InvoiceItemController {

    private final InvoiceItemService invoiceItemService;

    @Autowired
    public InvoiceItemController(InvoiceItemService invoiceItemService) {
        this.invoiceItemService = invoiceItemService;
    }

    @PostMapping
    public ResponseEntity<InvoiceItem> createInvoiceItem(@Valid @RequestBody InvoiceItemInsertRequestDTO invoiceInsertRequestDTO) {
        InvoiceItem createdInvoiceItem = invoiceItemService.createInvoiceItem(invoiceInsertRequestDTO);
        return new ResponseEntity<>(createdInvoiceItem, HttpStatus.CREATED);
    }

    @GetMapping("/by-invoice/{invoiceId}")
    public ResponseEntity<List<InvoiceItem>> getInvoiceItemsByInvoiceId(@PathVariable Long invoiceId) {
        List<InvoiceItem> invoiceItems = invoiceItemService.getInvoiceItemsByInvoiceId(invoiceId);
        return new ResponseEntity<>(invoiceItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceItem> getInvoiceItemById(@PathVariable Long id) {
        InvoiceItem invoiceItem = invoiceItemService.getInvoiceItemById(id).orElseThrow(() -> new InvoiceNotFoundException("InvoiceItem sa id-jem " + id + " ne postoji u bazi"));
        return new ResponseEntity<>(invoiceItem, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<InvoiceItem> updateInvoiceItem(@Valid @RequestBody InvoiceItemUpdateRequestDTO invoiceItemUpdateRequestDTO) {
        InvoiceItem updatedInvoiceItem = invoiceItemService.updateInvoiceItem(invoiceItemUpdateRequestDTO);
        return new ResponseEntity<>(updatedInvoiceItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Long>> deleteInvoiceItem(@PathVariable Long id) {
        invoiceItemService.deleteInvoiceItem(id);
        Map<String, Long> response = new HashMap<>();
        response.put("deletedId", id);
        return ResponseEntity.ok(response);
    }
}

