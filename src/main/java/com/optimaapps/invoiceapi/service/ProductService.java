package com.optimaapps.invoiceapi.service;

import com.optimaapps.invoiceapi.dto.ProductInsertRequestDTO;
import com.optimaapps.invoiceapi.dto.ProductUpdateRequestDTO;
import com.optimaapps.invoiceapi.entity.Product;
import com.optimaapps.invoiceapi.exception.ProductNotFoundException;
import com.optimaapps.invoiceapi.mapper.ProductInsertRequestMapper;
import com.optimaapps.invoiceapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductInsertRequestDTO productInsertRequestDTO) {
        Product createdProduct = ProductInsertRequestMapper.toProduct(productInsertRequestDTO);
        return productRepository.save(createdProduct);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public Product updateProduct(ProductUpdateRequestDTO productUpdateRequestDTO) {
        Product existingProduct = productRepository.findById(productUpdateRequestDTO.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product sa id-jem " + productUpdateRequestDTO.getProductId() + " nije pronadjen"));

        Product updatedProduct = setProductForUpdate(existingProduct, productUpdateRequestDTO);
        return productRepository.save(updatedProduct);
    }

    private Product setProductForUpdate(Product existingProduct, ProductUpdateRequestDTO productUpdateRequestDTO) {
        if(productUpdateRequestDTO.getName() != null && !productUpdateRequestDTO.getName().trim().isEmpty()){
            existingProduct.setName(productUpdateRequestDTO.getName());
        }
        if(productUpdateRequestDTO.getPrice() != null){
            existingProduct.setPrice(productUpdateRequestDTO.getPrice());
        }
        return existingProduct;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
