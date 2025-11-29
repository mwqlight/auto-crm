package com.stellarcrm.service.impl;

import com.stellarcrm.domain.customer.Customer;
import com.stellarcrm.domain.customer.CustomerStatus;
import com.stellarcrm.domain.customer.CustomerTier;
import com.stellarcrm.dto.customer.CustomerCreateRequest;
import com.stellarcrm.dto.customer.CustomerResponse;
import com.stellarcrm.dto.customer.CustomerUpdateRequest;
import com.stellarcrm.repository.CustomerRepository;
import com.stellarcrm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public CustomerResponse createCustomer(CustomerCreateRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setCompany(request.getCompany());
        customer.setIndustry(request.getIndustry());
        customer.setAddress(request.getAddress());
        customer.setStatus(request.getStatus());
        customer.setTier(request.getTier());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        
        Customer savedCustomer = customerRepository.save(customer);
        return convertToResponse(savedCustomer);
    }
    
    @Override
    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        return convertToResponse(customer);
    }
    
    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public CustomerResponse updateCustomer(Long id, CustomerUpdateRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setCompany(request.getCompany());
        customer.setIndustry(request.getIndustry());
        customer.setAddress(request.getAddress());
        customer.setStatus(request.getStatus());
        customer.setTier(request.getTier());
        customer.setLifetimeValue(request.getLifetimeValue());
        customer.setUpdatedAt(LocalDateTime.now());
        
        Customer updatedCustomer = customerRepository.save(customer);
        return convertToResponse(updatedCustomer);
    }
    
    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }
    
    @Override
    public List<CustomerResponse> searchCustomers(String keyword) {
        return customerRepository.searchByNameOrCompany(keyword).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<CustomerResponse> getCustomersByStatus(CustomerStatus status) {
        return customerRepository.findByStatus(status).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<CustomerResponse> getCustomersByTier(CustomerTier tier) {
        return customerRepository.findByTier(tier).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    private CustomerResponse convertToResponse(Customer customer) {
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setName(customer.getName());
        response.setEmail(customer.getEmail());
        response.setPhone(customer.getPhone());
        response.setCompany(customer.getCompany());
        response.setIndustry(customer.getIndustry());
        response.setAddress(customer.getAddress());
        response.setStatus(customer.getStatus());
        response.setTier(customer.getTier());
        response.setLifetimeValue(customer.getLifetimeValue());
        response.setCreatedAt(customer.getCreatedAt());
        response.setUpdatedAt(customer.getUpdatedAt());
        return response;
    }
}