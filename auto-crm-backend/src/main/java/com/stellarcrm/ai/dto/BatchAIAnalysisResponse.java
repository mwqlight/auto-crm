package com.stellarcrm.ai.dto;

import java.util.List;

public class BatchAIAnalysisResponse {
    private List<AIAnalysisResponse> results;
    private int successCount;
    private int failureCount;

    // Getters and setters
    public List<AIAnalysisResponse> getResults() {
        return results;
    }

    public void setResults(List<AIAnalysisResponse> results) {
        this.results = results;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public int getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(int failureCount) {
        this.failureCount = failureCount;
    }
}