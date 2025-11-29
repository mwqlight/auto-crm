package com.stellarcrm.service;

import com.stellarcrm.domain.sales.OpportunityStage;
import com.stellarcrm.dto.sales.OpportunityCreateRequest;
import com.stellarcrm.dto.sales.OpportunityResponse;
import com.stellarcrm.dto.sales.OpportunityUpdateRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface SalesService {
    OpportunityResponse createOpportunity(OpportunityCreateRequest request);
    
    OpportunityResponse getOpportunityById(Long id);
    
    List<OpportunityResponse> getAllOpportunities();
    
    OpportunityResponse updateOpportunity(Long id, OpportunityUpdateRequest request);
    
    void deleteOpportunity(Long id);
    
    List<OpportunityResponse> getOpportunitiesByStage(OpportunityStage stage);
    
    List<OpportunityResponse> getOpportunitiesByValueRange(BigDecimal minValue, BigDecimal maxValue);
    
    List<OpportunityResponse> getOpportunitiesByCloseDateRange(LocalDateTime startDate, LocalDateTime endDate);
    
    BigDecimal getTotalValueByStage(OpportunityStage stage);
}