package com.stellarcrm.service.impl;

import com.stellarcrm.domain.customer.Customer;
import com.stellarcrm.domain.sales.Opportunity;
import com.stellarcrm.domain.sales.OpportunityStage;
import com.stellarcrm.dto.customer.CustomerResponse;
import com.stellarcrm.dto.sales.OpportunityCreateRequest;
import com.stellarcrm.dto.sales.OpportunityResponse;
import com.stellarcrm.dto.sales.OpportunityUpdateRequest;
import com.stellarcrm.repository.CustomerRepository;
import com.stellarcrm.repository.OpportunityRepository;
import com.stellarcrm.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesServiceImpl implements SalesService {
    
    @Autowired
    private OpportunityRepository opportunityRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public OpportunityResponse createOpportunity(OpportunityCreateRequest request) {
        Opportunity opportunity = new Opportunity();
        opportunity.setName(request.getName());
        opportunity.setValue(request.getValue());
        opportunity.setDescription(request.getDescription());
        opportunity.setStage(request.getStage());
        opportunity.setProbability(request.getProbability());
        opportunity.setExpectedCloseDate(request.getExpectedCloseDate());
        opportunity.setCreatedAt(LocalDateTime.now());
        opportunity.setUpdatedAt(LocalDateTime.now());
        
        // 设置客户关联
        if (request.getCustomerId() != null) {
            Customer customer = customerRepository.findById(request.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found with id: " + request.getCustomerId()));
            opportunity.setCustomer(customer);
        }
        
        Opportunity savedOpportunity = opportunityRepository.save(opportunity);
        return convertToResponse(savedOpportunity);
    }
    
    @Override
    public OpportunityResponse getOpportunityById(Long id) {
        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Opportunity not found with id: " + id));
        return convertToResponse(opportunity);
    }
    
    @Override
    public List<OpportunityResponse> getAllOpportunities() {
        return opportunityRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public OpportunityResponse updateOpportunity(Long id, OpportunityUpdateRequest request) {
        Opportunity opportunity = opportunityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Opportunity not found with id: " + id));
        
        opportunity.setName(request.getName());
        opportunity.setValue(request.getValue());
        opportunity.setDescription(request.getDescription());
        opportunity.setStage(request.getStage());
        opportunity.setProbability(request.getProbability());
        opportunity.setExpectedCloseDate(request.getExpectedCloseDate());
        opportunity.setUpdatedAt(LocalDateTime.now());
        
        // 更新客户关联
        if (request.getCustomerId() != null) {
            Customer customer = customerRepository.findById(request.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found with id: " + request.getCustomerId()));
            opportunity.setCustomer(customer);
        }
        
        Opportunity updatedOpportunity = opportunityRepository.save(opportunity);
        return convertToResponse(updatedOpportunity);
    }
    
    @Override
    public void deleteOpportunity(Long id) {
        if (!opportunityRepository.existsById(id)) {
            throw new RuntimeException("Opportunity not found with id: " + id);
        }
        opportunityRepository.deleteById(id);
    }
    
    @Override
    public List<OpportunityResponse> getOpportunitiesByStage(OpportunityStage stage) {
        return opportunityRepository.findByStage(stage).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<OpportunityResponse> getOpportunitiesByValueRange(BigDecimal minValue, BigDecimal maxValue) {
        return opportunityRepository.findByValueRange(minValue, maxValue).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<OpportunityResponse> getOpportunitiesByCloseDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return opportunityRepository.findByExpectedCloseDateBetween(startDate, endDate).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public BigDecimal getTotalValueByStage(OpportunityStage stage) {
        return opportunityRepository.sumValueByStage(stage);
    }
    
    private OpportunityResponse convertToResponse(Opportunity opportunity) {
        OpportunityResponse response = new OpportunityResponse();
        response.setId(opportunity.getId());
        response.setName(opportunity.getName());
        response.setValue(opportunity.getValue());
        response.setDescription(opportunity.getDescription());
        response.setStage(opportunity.getStage());
        response.setProbability(opportunity.getProbability());
        response.setExpectedCloseDate(opportunity.getExpectedCloseDate());
        response.setCreatedAt(opportunity.getCreatedAt());
        response.setUpdatedAt(opportunity.getUpdatedAt());
        
        // 转换客户信息
        if (opportunity.getCustomer() != null) {
            Customer customer = opportunity.getCustomer();
            CustomerResponse customerResponse = new CustomerResponse();
            customerResponse.setId(customer.getId());
            customerResponse.setName(customer.getName());
            customerResponse.setEmail(customer.getEmail());
            customerResponse.setPhone(customer.getPhone());
            customerResponse.setCompany(customer.getCompany());
            customerResponse.setIndustry(customer.getIndustry());
            customerResponse.setAddress(customer.getAddress());
            customerResponse.setStatus(customer.getStatus());
            customerResponse.setTier(customer.getTier());
            customerResponse.setLifetimeValue(customer.getLifetimeValue());
            customerResponse.setCreatedAt(customer.getCreatedAt());
            customerResponse.setUpdatedAt(customer.getUpdatedAt());
            response.setCustomer(customerResponse);
        }
        
        return response;
    }
}