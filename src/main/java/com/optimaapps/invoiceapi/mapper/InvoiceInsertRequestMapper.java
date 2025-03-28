package com.optimaapps.invoiceapi.mapper;

import com.optimaapps.invoiceapi.dto.InvoiceInsertRequestDTO;
import com.optimaapps.invoiceapi.entity.Customer;
import com.optimaapps.invoiceapi.entity.Invoice;

public class InvoiceInsertRequestMapper {

    public static Invoice toInvoice(InvoiceInsertRequestDTO invoiceInsertRequestDTO) {
        Invoice invoice = new Invoice();
        invoice.setTotalAmount(invoiceInsertRequestDTO.getTotalAmount());
        invoice.setInvoiceNumber(generateInvoiceNumber());
        Customer customer = new Customer();
        customer.setId(invoiceInsertRequestDTO.getCustomerId());
        invoice.setCustomer(customer);
        return invoice;
    }

    private static String generateInvoiceNumber(){
        return "INV-" + System.currentTimeMillis();
    }
}
