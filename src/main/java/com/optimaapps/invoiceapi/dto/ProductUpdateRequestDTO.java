package com.optimaapps.invoiceapi.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequestDTO {

    @NotNull
    private Long productId;

    @Size(min = 2, max = 30, message = "Polje name mora da ima između 2 i 30 karaktera")
    private String name;

    @DecimalMin(value = "0.0")
    private BigDecimal price;
}
