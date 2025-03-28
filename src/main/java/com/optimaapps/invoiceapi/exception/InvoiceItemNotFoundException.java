package com.optimaapps.invoiceapi.exception;

public class InvoiceItemNotFoundException extends RuntimeException{
    public InvoiceItemNotFoundException(String message) {
        super(message);
    }
}
