package com.stellarcrm.ai.dto;

import lombok.Data;
import com.stellarcrm.ai.enums.EntityType;
import com.stellarcrm.ai.enums.AnalysisType;

@Data
public class AIAnalysisRequest {
    private Long entityId; // 客户ID或机会ID
    private EntityType entityType; // CUSTOMER 或 OPPORTUNITY
    private AnalysisType analysisType; // 分析类型
}