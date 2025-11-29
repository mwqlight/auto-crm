package com.stellarcrm.ai.service;

import com.stellarcrm.ai.dto.*;
import com.stellarcrm.ai.enums.AnalysisType;
import com.stellarcrm.ai.enums.EntityType;

import java.util.concurrent.CompletableFuture;

public interface AIAnalysisService {
    CompletableFuture<AIAnalysisResponse> analyze(AIAnalysisRequest request);
    CompletableFuture<AIAnalysisResponse> advancedAnalyze(AIAnalysisQueryRequest request);
    CompletableFuture<AIAnalysisDetailedResponse> getDetailedAnalysis(Long analysisId);
    CompletableFuture<BatchAIAnalysisResponse> batchAnalyze(BatchAIAnalysisRequest request);
    CompletableFuture<AIAnalysisResponse> getAnalysisByRiskLevel(String riskLevel);
}