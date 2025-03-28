package com.optimaapps.invoiceapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerUpdateRequestDTO {

    @NotNull
    private Long customerId;

    @Size(min = 2, max = 30, message = "Polje firstName mora da ima između 2 i 30 karaktera")
    private String firstName;

    @Size(min = 2, max = 30, message = "Polje lastName mora da ima između 2 i 30 karaktera")
    private String lastName;

    @Size(min = 2, max = 50, message = "Polje adress mora da ima između 2 i 50 karaktera")
    private String address;

}
