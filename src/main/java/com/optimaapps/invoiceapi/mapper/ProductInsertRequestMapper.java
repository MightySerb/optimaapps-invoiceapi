package com.optimaapps.invoiceapi.mapper;

import com.optimaapps.invoiceapi.dto.ProductInsertRequestDTO;
import com.optimaapps.invoiceapi.entity.Product;

public class ProductInsertRequestMapper {
    public static Product toProduct(ProductInsertRequestDTO productInsertRequestDTO) {
        Product product = new Product();
        product.setName(productInsertRequestDTO.getName());
        product.setPrice(productInsertRequestDTO.getPrice());
        return product;
    }
}
