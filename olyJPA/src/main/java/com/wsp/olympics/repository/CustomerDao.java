package com.wsp.olympics.repository;

import com.wsp.olympics.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Performs various customer-related operations
 * 
 * @author WSP
 */
@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
}
