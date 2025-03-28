package com.optimaapps.invoiceapi.service;

import com.optimaapps.invoiceapi.dto.CustomerInsertRequestDTO;
import com.optimaapps.invoiceapi.dto.CustomerUpdateRequestDTO;
import com.optimaapps.invoiceapi.entity.Customer;
import com.optimaapps.invoiceapi.exception.CustomerNotFoundException;
import com.optimaapps.invoiceapi.mapper.CustomerInsertRequestMapper;
import com.optimaapps.invoiceapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(CustomerInsertRequestDTO customerInsertRequestDTO) {
        Customer createdCustomer = CustomerInsertRequestMapper.toCustomer(customerInsertRequestDTO);
        return customerRepository.save(createdCustomer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer updateCustomer(CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        Customer existingCustomer = customerRepository.findById(customerUpdateRequestDTO.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer sa id-jem " + customerUpdateRequestDTO.getCustomerId() + " nije pronadjen"));

        Customer updateCustomer = setCustomerForUpdate(existingCustomer, customerUpdateRequestDTO);
        return customerRepository.save(updateCustomer);
    }

    private Customer setCustomerForUpdate(Customer existingCustomer, CustomerUpdateRequestDTO customerUpdateRequestDTO) {
        if(customerUpdateRequestDTO.getFirstName() != null && !customerUpdateRequestDTO.getFirstName().trim().isEmpty()){
            existingCustomer.setFirstName(customerUpdateRequestDTO.getFirstName());
        }
        if(customerUpdateRequestDTO.getLastName() != null && !customerUpdateRequestDTO.getLastName().trim().isEmpty()){
            existingCustomer.setLastName(customerUpdateRequestDTO.getLastName());
        }
        if(customerUpdateRequestDTO.getAddress() != null && !customerUpdateRequestDTO.getAddress().trim().isEmpty()){
            existingCustomer.setAddress(customerUpdateRequestDTO.getAddress());
        }

        return existingCustomer;
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
