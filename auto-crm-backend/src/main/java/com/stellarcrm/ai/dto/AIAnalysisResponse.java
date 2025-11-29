package com.stellarcrm.ai.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AIAnalysisResponse {
    private Long id;
    private Long entityId;
    private String entityType;
    private String analysisType;
    private Double confidenceScore;
    private String prediction;
    private String riskLevel;
    private String recommendations;
    private String analysisData;
    private LocalDateTime createdAt;
}