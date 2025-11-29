package com.stellarcrm.dto.sales;

import com.stellarcrm.domain.sales.OpportunityStage;
import com.stellarcrm.dto.customer.CustomerResponse;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OpportunityResponse {
    private Long id;
    private String name;
    private CustomerResponse customer;
    private BigDecimal value;
    private String description;
    private OpportunityStage stage;
    private Double probability;
    private LocalDateTime expectedCloseDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}