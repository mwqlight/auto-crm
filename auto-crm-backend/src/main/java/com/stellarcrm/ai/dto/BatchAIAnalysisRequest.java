package com.stellarcrm.ai.dto;

import java.util.List;

public class BatchAIAnalysisRequest {
    private List<AIAnalysisRequest> requests;

    // Getters and setters
    public List<AIAnalysisRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<AIAnalysisRequest> requests) {
        this.requests = requests;
    }
}