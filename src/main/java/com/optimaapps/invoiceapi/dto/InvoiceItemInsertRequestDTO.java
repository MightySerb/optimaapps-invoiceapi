package com.optimaapps.invoiceapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceItemInsertRequestDTO {

    @NotNull
    private Long invoiceId;

    @NotNull
    private Long productId;

    @NotNull
    @Min(value = 1, message = "Quantity must be at least 1.")
    private Integer quantity;

}
