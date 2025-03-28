package com.optimaapps.invoiceapi.controller;

import com.optimaapps.invoiceapi.dto.CustomerInsertRequestDTO;
import com.optimaapps.invoiceapi.dto.CustomerUpdateRequestDTO;
import com.optimaapps.invoiceapi.entity.Customer;
import com.optimaapps.invoiceapi.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CustomerInsertRequestDTO customerInsertRequestDTO) {
        Customer createdCustomer = customerService.createCustomer(customerInsertRequestDTO);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        Customer updatedCustomer = customerService.updateCustomer(customerUpdateRequestDTO);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Long>> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        Map<String, Long> response = new HashMap<>();
        response.put("deletedId", id);
        return ResponseEntity.ok(response);
    }
}
