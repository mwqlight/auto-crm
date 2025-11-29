package com.stellarcrm.dto.sales;

import com.stellarcrm.domain.sales.OpportunityStage;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OpportunityUpdateRequest {
    private String name;
    private Long customerId;
    private BigDecimal value;
    private String description;
    private OpportunityStage stage;
    private Double probability;
    private LocalDateTime expectedCloseDate;
}