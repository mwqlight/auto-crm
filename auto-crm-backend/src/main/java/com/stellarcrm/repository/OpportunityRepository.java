package com.stellarcrm.repository;

import com.stellarcrm.domain.sales.Opportunity;
import com.stellarcrm.domain.sales.OpportunityStage;
import com.stellarcrm.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
    List<Opportunity> findByCustomer(Customer customer);
    
    List<Opportunity> findByStage(OpportunityStage stage);
    
    @Query("SELECT o FROM Opportunity o WHERE o.value >= :minValue AND o.value <= :maxValue")
    List<Opportunity> findByValueRange(@Param("minValue") BigDecimal minValue, @Param("maxValue") BigDecimal maxValue);
    
    List<Opportunity> findByExpectedCloseDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT SUM(o.value) FROM Opportunity o WHERE o.stage = :stage")
    BigDecimal sumValueByStage(@Param("stage") OpportunityStage stage);
}