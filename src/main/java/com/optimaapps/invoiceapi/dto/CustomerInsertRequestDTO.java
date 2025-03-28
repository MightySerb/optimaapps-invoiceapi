package com.optimaapps.invoiceapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerInsertRequestDTO {
    @NotBlank
    @Size(min = 2, max = 30, message = "Polje firstName mora biti između 2 i 30 karaktera")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 30, message = "Polje lastName mora biti između 2 i 30 karaktera")
    private String lastName;

    @NotBlank
    @Size(min = 2, max = 50, message = "Polje Adress mora biti između 2 i 30 karaktera")
    private String address;
}
