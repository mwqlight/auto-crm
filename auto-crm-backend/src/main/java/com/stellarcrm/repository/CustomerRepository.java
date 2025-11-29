package com.stellarcrm.repository;

import com.stellarcrm.domain.customer.Customer;
import com.stellarcrm.domain.customer.CustomerStatus;
import com.stellarcrm.domain.customer.CustomerTier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    
    List<Customer> findByStatus(CustomerStatus status);
    
    List<Customer> findByTier(CustomerTier tier);
    
    @Query("SELECT c FROM Customer c WHERE c.name LIKE %:keyword% OR c.company LIKE %:keyword%")
    List<Customer> searchByNameOrCompany(@Param("keyword") String keyword);
    
    @Query("SELECT COUNT(c) FROM Customer c WHERE c.status = :status")
    Long countByStatus(@Param("status") CustomerStatus status);
}