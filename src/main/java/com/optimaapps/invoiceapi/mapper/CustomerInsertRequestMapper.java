package com.optimaapps.invoiceapi.mapper;

import com.optimaapps.invoiceapi.dto.CustomerInsertRequestDTO;
import com.optimaapps.invoiceapi.entity.Customer;

public class CustomerInsertRequestMapper {

    private CustomerInsertRequestMapper(){}

    public static Customer toCustomer(CustomerInsertRequestDTO customerInsertRequestDTO) {
        Customer customer = new Customer();
        customer.setFirstName(customerInsertRequestDTO.getFirstName());
        customer.setLastName(customerInsertRequestDTO.getLastName());
        customer.setAddress(customerInsertRequestDTO.getAddress());
        return customer;
    }
}
