package com.optimaapps.invoiceapi.service;

import com.optimaapps.invoiceapi.dto.InvoiceInsertRequestDTO;
import com.optimaapps.invoiceapi.dto.InvoiceUpdateRequestDTO;
import com.optimaapps.invoiceapi.entity.Customer;
import com.optimaapps.invoiceapi.entity.Invoice;
import com.optimaapps.invoiceapi.exception.CustomerNotFoundException;
import com.optimaapps.invoiceapi.exception.InvoiceNotFoundException;
import com.optimaapps.invoiceapi.mapper.InvoiceInsertRequestMapper;
import com.optimaapps.invoiceapi.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CustomerService customerService;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, CustomerService customerService) {
        this.invoiceRepository = invoiceRepository;
        this.customerService = customerService;
    }

    public Invoice createInvoice(InvoiceInsertRequestDTO invoiceInsertRequestDTO) {
        Invoice createdInvoice = InvoiceInsertRequestMapper.toInvoice(invoiceInsertRequestDTO);
        return invoiceRepository.save(createdInvoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    public Invoice updateInvoice(InvoiceUpdateRequestDTO invoiceUpdateRequestDTO) {
        Invoice existingInvoice = invoiceRepository.findById(invoiceUpdateRequestDTO.getInvoiceId())
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice sa id-jem " + invoiceUpdateRequestDTO.getInvoiceId() + " nije pronadjen"));

        Invoice updateInvoice = setInvoiceForUpdate(existingInvoice, invoiceUpdateRequestDTO);
        return invoiceRepository.save(updateInvoice);
    }

    private Invoice setInvoiceForUpdate(Invoice existingInvoice, InvoiceUpdateRequestDTO invoiceUpdateRequestDTO){
        if(invoiceUpdateRequestDTO.getTotalAmount() != null){
            existingInvoice.setTotalAmount(invoiceUpdateRequestDTO.getTotalAmount());
        }
        if(invoiceUpdateRequestDTO.getCustomerId() != null){
            Customer customer = customerService.getCustomerById(invoiceUpdateRequestDTO.getCustomerId())
                    .orElseThrow(() -> new CustomerNotFoundException("Customer sa id-jem " + invoiceUpdateRequestDTO.getCustomerId() + " nije pronadjen"));
            existingInvoice.setCustomer(customer);
        }
        return existingInvoice;
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}
