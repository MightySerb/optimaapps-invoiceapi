package com.optimaapps.invoiceapi.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceInsertRequestDTO {

    @NotNull
    private Long customerId;

    @NotNull
    @DecimalMin(value = "0.00")
    private BigDecimal totalAmount;

}



