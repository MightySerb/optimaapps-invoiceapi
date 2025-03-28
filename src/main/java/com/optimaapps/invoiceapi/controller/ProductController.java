package com.optimaapps.invoiceapi.controller;

import com.optimaapps.invoiceapi.dto.ProductInsertRequestDTO;
import com.optimaapps.invoiceapi.dto.ProductUpdateRequestDTO;
import com.optimaapps.invoiceapi.entity.Product;
import com.optimaapps.invoiceapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductInsertRequestDTO productInsertRequestDTO) {
        Product createdProduct = productService.createProduct(productInsertRequestDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody ProductUpdateRequestDTO productUpdateRequestDTO) {
        Product updatedProduct = productService.updateProduct(productUpdateRequestDTO);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Long>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        Map<String, Long> response = new HashMap<>();
        response.put("deletedId", id);
        return ResponseEntity.ok(response);
    }
}
