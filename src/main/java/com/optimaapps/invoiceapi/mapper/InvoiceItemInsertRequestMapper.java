package com.optimaapps.invoiceapi.mapper;

import com.optimaapps.invoiceapi.dto.InvoiceItemInsertRequestDTO;
import com.optimaapps.invoiceapi.entity.Customer;
import com.optimaapps.invoiceapi.entity.Invoice;
import com.optimaapps.invoiceapi.entity.InvoiceItem;
import com.optimaapps.invoiceapi.entity.Product;

import java.math.BigDecimal;

public class InvoiceItemInsertRequestMapper {

    private InvoiceItemInsertRequestMapper(){}

    public static InvoiceItem toInvoice(InvoiceItemInsertRequestDTO invoiceItemInsertRequestDTO, BigDecimal amount) {
        InvoiceItem invoiceItem = new InvoiceItem();
        Invoice invoice = new Invoice();
        invoice.setId(invoiceItemInsertRequestDTO.getInvoiceId());
        invoiceItem.setInvoice(invoice);
        Product product = new Product();
        product.setId(invoiceItemInsertRequestDTO.getProductId());
        invoiceItem.setProduct(product);
        invoiceItem.setQuantity(invoiceItemInsertRequestDTO.getQuantity());
        invoiceItem.setAmount(amount);
        return invoiceItem;
    }
}
