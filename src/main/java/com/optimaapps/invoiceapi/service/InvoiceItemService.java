package com.optimaapps.invoiceapi.service;

import com.optimaapps.invoiceapi.dto.InvoiceItemInsertRequestDTO;
import com.optimaapps.invoiceapi.dto.InvoiceItemUpdateRequestDTO;
import com.optimaapps.invoiceapi.entity.Invoice;
import com.optimaapps.invoiceapi.entity.InvoiceItem;
import com.optimaapps.invoiceapi.entity.Product;
import com.optimaapps.invoiceapi.exception.InvoiceItemNotFoundException;
import com.optimaapps.invoiceapi.exception.ProductNotFoundException;
import com.optimaapps.invoiceapi.mapper.InvoiceItemInsertRequestMapper;
import com.optimaapps.invoiceapi.repository.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceItemService {

    private final InvoiceItemRepository invoiceItemRepository;
    private final ProductService productService;
    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceItemService(InvoiceItemRepository invoiceItemRepository, ProductService productService, InvoiceService invoiceService) {
        this.invoiceItemRepository = invoiceItemRepository;
        this.productService = productService;
        this.invoiceService = invoiceService;
    }

    public InvoiceItem createInvoiceItem(InvoiceItemInsertRequestDTO invoiceItemInsertRequestDTO) {
        BigDecimal amount = calculateAmount(invoiceItemInsertRequestDTO.getQuantity(), invoiceItemInsertRequestDTO.getProductId());
        InvoiceItem createdInvoiceItem = InvoiceItemInsertRequestMapper.toInvoice(invoiceItemInsertRequestDTO, amount);
        return invoiceItemRepository.save(createdInvoiceItem);
    }

    private BigDecimal calculateAmount(Integer quantity, Long productId){
        Product product = productService.getProductById(productId).orElseThrow(() -> new ProductNotFoundException("Product sa id-jem " + productId + " nije pronadjen"));
        BigDecimal productPrice = product.getPrice();
        return productPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public List<InvoiceItem> getInvoiceItemsByInvoiceId(Long invoiceId) {
        return invoiceItemRepository.findByInvoiceId(invoiceId);
    }

    public Optional<InvoiceItem> getInvoiceItemById(Long id) {
        return invoiceItemRepository.findById(id);
    }

    public InvoiceItem updateInvoiceItem(InvoiceItemUpdateRequestDTO invoiceItemUpdateRequestDTO) {
        InvoiceItem existingInvoiceItem = invoiceItemRepository.findById(invoiceItemUpdateRequestDTO.getInvoiceItemId())
                .orElseThrow(() -> new InvoiceItemNotFoundException("InvoiceItem sa id-jem " + invoiceItemUpdateRequestDTO.getInvoiceItemId() + " nije pronadjen"));
        InvoiceItem updateInvoiceItem = setInvoiceItemForUpdate(existingInvoiceItem, invoiceItemUpdateRequestDTO);
        return invoiceItemRepository.save(updateInvoiceItem);
    }

    private InvoiceItem setInvoiceItemForUpdate(InvoiceItem existingInvoiceItem, InvoiceItemUpdateRequestDTO invoiceItemUpdateRequestDTO) {
        if(invoiceItemUpdateRequestDTO.getInvoiceId() != null){
            Invoice invoice = invoiceService.getInvoiceById(invoiceItemUpdateRequestDTO.getInvoiceId())
                    .orElseThrow(() -> new InvoiceItemNotFoundException("Invoice sa id-jem " + invoiceItemUpdateRequestDTO.getInvoiceId() + " nije pronadjen"));
            existingInvoiceItem.setInvoice(invoice);
        }
        if(invoiceItemUpdateRequestDTO.getProductId() != null){
            Product product = productService.getProductById(invoiceItemUpdateRequestDTO.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product sa id-jem " + invoiceItemUpdateRequestDTO.getProductId() + " nije pronadjen"));
            existingInvoiceItem.setProduct(product);
        }
        if(invoiceItemUpdateRequestDTO.getQuantity() != null){
            existingInvoiceItem.setQuantity(invoiceItemUpdateRequestDTO.getQuantity());
            BigDecimal newAmount = calculateAmount(invoiceItemUpdateRequestDTO.getQuantity(), invoiceItemUpdateRequestDTO.getProductId());
            existingInvoiceItem.setAmount(newAmount);
        }
        return existingInvoiceItem;
    }

    public void deleteInvoiceItem(Long id) {
        invoiceItemRepository.deleteById(id);
    }
}
