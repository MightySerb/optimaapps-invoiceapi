package com.optimaapps.invoiceapi.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInsertRequestDTO {
    @NotBlank
    @Size(min = 2, max = 30, message = "Polje product mora da ima između 2 i 30 karaktera")
    private String name;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal price;
}
