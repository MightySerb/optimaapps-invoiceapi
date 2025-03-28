package com.optimaapps.invoiceapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InvoiceItemUpdateRequestDTO {
    @NotNull
    private Long invoiceItemId;

    private Long invoiceId;

    private Long productId;

    @Min(value = 1)
    private Integer quantity;
}
