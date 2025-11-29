package com.stellarcrm.service;

import com.stellarcrm.domain.customer.Customer;
import com.stellarcrm.domain.customer.CustomerStatus;
import com.stellarcrm.domain.customer.CustomerTier;
import com.stellarcrm.dto.customer.CustomerCreateRequest;
import com.stellarcrm.dto.customer.CustomerResponse;
import com.stellarcrm.dto.customer.CustomerUpdateRequest;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerCreateRequest request);
    
    CustomerResponse getCustomerById(Long id);
    
    List<CustomerResponse> getAllCustomers();
    
    CustomerResponse updateCustomer(Long id, CustomerUpdateRequest request);
    
    void deleteCustomer(Long id);
    
    List<CustomerResponse> searchCustomers(String keyword);
    
    List<CustomerResponse> getCustomersByStatus(CustomerStatus status);
    
    List<CustomerResponse> getCustomersByTier(CustomerTier tier);
}