package com.stellarcrm.ai.dto;

import com.stellarcrm.ai.enums.EntityType;
import com.stellarcrm.ai.enums.AnalysisType;
import java.util.Map;

public class AIAnalysisQueryRequest {
    private Long entityId;
    private EntityType entityType;
    private AnalysisType analysisType;
    private Map<String, Object> parameters;

    // Getters and setters
    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public AnalysisType getAnalysisType() {
        return analysisType;
    }

    public void setAnalysisType(AnalysisType analysisType) {
        this.analysisType = analysisType;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}