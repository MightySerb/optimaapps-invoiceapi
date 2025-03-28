package com.optimaapps.invoiceapi.repository;

import com.optimaapps.invoiceapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
