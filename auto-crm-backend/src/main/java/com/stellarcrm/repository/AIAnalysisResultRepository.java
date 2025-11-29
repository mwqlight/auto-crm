package com.stellarcrm.repository;

import com.stellarcrm.ai.AIAnalysisResult;
import com.stellarcrm.domain.customer.Customer;
import com.stellarcrm.domain.sales.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AIAnalysisResultRepository extends JpaRepository<AIAnalysisResult, Long> {
    List<AIAnalysisResult> findByCustomer(Customer customer);
    
    List<AIAnalysisResult> findByOpportunity(Opportunity opportunity);
    
    List<AIAnalysisResult> findByAnalysisType(String analysisType);
    
    List<AIAnalysisResult> findByRiskLevel(String riskLevel);
}