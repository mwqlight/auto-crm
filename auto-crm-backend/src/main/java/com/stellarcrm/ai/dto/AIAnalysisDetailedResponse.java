package com.stellarcrm.ai.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class AIAnalysisDetailedResponse {
    private Long id;
    private Long entityId;
    private String entityType;
    private String analysisType;
    private Double confidenceScore;
    private String riskLevel;
    private String recommendations;
    private Map<String, Object> detailedAnalysisData;
    private LocalDateTime createdAt;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getAnalysisType() {
        return analysisType;
    }

    public void setAnalysisType(String analysisType) {
        this.analysisType = analysisType;
    }

    public Double getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(Double confidenceScore) {
        this.confidenceScore = confidenceScore;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public Map<String, Object> getDetailedAnalysisData() {
        return detailedAnalysisData;
    }

    public void setDetailedAnalysisData(Map<String, Object> detailedAnalysisData) {
        this.detailedAnalysisData = detailedAnalysisData;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}