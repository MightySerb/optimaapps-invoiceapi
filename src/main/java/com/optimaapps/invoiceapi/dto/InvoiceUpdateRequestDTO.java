package com.optimaapps.invoiceapi.dto;

import com.optimaapps.invoiceapi.entity.Customer;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceUpdateRequestDTO {
    @NotNull
    private Long invoiceId;

    private Long customerId;

    @DecimalMin(value = "0.00")
    private BigDecimal totalAmount;
}
