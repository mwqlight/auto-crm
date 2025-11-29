package com.stellarcrm.ai.controller;

import com.stellarcrm.ai.dto.*;
import com.stellarcrm.ai.enums.AnalysisType;
import com.stellarcrm.ai.enums.EntityType;
import com.stellarcrm.ai.service.AIAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/ai-analysis")
public class AIAnalysisController {

    @Autowired
    private AIAnalysisService aiAnalysisService;

    @PostMapping("/analyze")
    public CompletableFuture<AIAnalysisResultWrapper<AIAnalysisResponse>> analyze(@RequestBody AIAnalysisRequest request) {
        return aiAnalysisService.analyze(request)
            .thenApply(AIAnalysisResultWrapper::success)
            .exceptionally(ex -> AIAnalysisResultWrapper.error("分析失败: " + ex.getMessage()));
    }

    @PostMapping("/advanced-analyze")
    public CompletableFuture<AIAnalysisResultWrapper<AIAnalysisResponse>> advancedAnalyze(@RequestBody AIAnalysisQueryRequest request) {
        return aiAnalysisService.advancedAnalyze(request)
            .thenApply(AIAnalysisResultWrapper::success)
            .exceptionally(ex -> AIAnalysisResultWrapper.error("高级分析失败: " + ex.getMessage()));
    }

    @GetMapping("/detailed/{analysisId}")
    public CompletableFuture<AIAnalysisResultWrapper<AIAnalysisDetailedResponse>> getDetailedAnalysis(@PathVariable Long analysisId) {
        return aiAnalysisService.getDetailedAnalysis(analysisId)
            .thenApply(AIAnalysisResultWrapper::success)
            .exceptionally(ex -> AIAnalysisResultWrapper.error("获取详细分析失败: " + ex.getMessage()));
    }

    @PostMapping("/batch-analyze")
    public CompletableFuture<AIAnalysisResultWrapper<BatchAIAnalysisResponse>> batchAnalyze(@RequestBody BatchAIAnalysisRequest request) {
        return aiAnalysisService.batchAnalyze(request)
            .thenApply(AIAnalysisResultWrapper::success)
            .exceptionally(ex -> AIAnalysisResultWrapper.error("批量分析失败: " + ex.getMessage()));
    }

    @GetMapping("/risk-level/{riskLevel}")
    public CompletableFuture<AIAnalysisResultWrapper<AIAnalysisResponse>> getAnalysisByRiskLevel(@PathVariable String riskLevel) {
        return aiAnalysisService.getAnalysisByRiskLevel(riskLevel)
            .thenApply(AIAnalysisResultWrapper::success)
            .exceptionally(ex -> AIAnalysisResultWrapper.error("获取风险等级分析失败: " + ex.getMessage()));
    }
}