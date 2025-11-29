package com.stellarcrm.ai.service;

import com.stellarcrm.ai.dto.AIAnalysisConfig;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AIModelConfigService {

    private final Map<String, AIAnalysisConfig> configMap = new HashMap<>();

    public AIModelConfigService() {
        // 初始化默认配置
        initializeDefaultConfigs();
    }

    private void initializeDefaultConfigs() {
        // 销售预测模型配置
        AIAnalysisConfig salesPredictionConfig = new AIAnalysisConfig();
        salesPredictionConfig.setModelVersion("v2.1");
        salesPredictionConfig.setConfidenceThreshold(0.8);
        salesPredictionConfig.setMaxRetries(3);
        salesPredictionConfig.setTimeoutMs(5000);
        configMap.put("SALES_PREDICTION", salesPredictionConfig);

        // 客户风险评估模型配置
        AIAnalysisConfig customerRiskConfig = new AIAnalysisConfig();
        customerRiskConfig.setModelVersion("v1.5");
        customerRiskConfig.setConfidenceThreshold(0.75);
        customerRiskConfig.setMaxRetries(2);
        customerRiskConfig.setTimeoutMs(3000);
        configMap.put("CUSTOMER_RISK_ASSESSMENT", customerRiskConfig);

        // 高级风险评估模型配置
        AIAnalysisConfig advancedRiskConfig = new AIAnalysisConfig();
        advancedRiskConfig.setModelVersion("v3.0");
        advancedRiskConfig.setConfidenceThreshold(0.9);
        advancedRiskConfig.setMaxRetries(3);
        advancedRiskConfig.setTimeoutMs(10000);
        configMap.put("ADVANCED_RISK_ASSESSMENT", advancedRiskConfig);

        // 行为分析模型配置
        AIAnalysisConfig behavioralConfig = new AIAnalysisConfig();
        behavioralConfig.setModelVersion("v2.3");
        behavioralConfig.setConfidenceThreshold(0.85);
        behavioralConfig.setMaxRetries(2);
        behavioralConfig.setTimeoutMs(4000);
        configMap.put("BEHAVIORAL_ANALYSIS", behavioralConfig);

        // 详细成功分析模型配置
        AIAnalysisConfig detailedSuccessConfig = new AIAnalysisConfig();
        detailedSuccessConfig.setModelVersion("v1.8");
        detailedSuccessConfig.setConfidenceThreshold(0.8);
        detailedSuccessConfig.setMaxRetries(2);
        detailedSuccessConfig.setTimeoutMs(6000);
        configMap.put("DETAILED_SUCCESS_ANALYSIS", detailedSuccessConfig);
    }

    public AIAnalysisConfig getConfigForAnalysisType(String analysisType) {
        return configMap.getOrDefault(analysisType, getDefaultConfig());
    }

    private AIAnalysisConfig getDefaultConfig() {
        AIAnalysisConfig defaultConfig = new AIAnalysisConfig();
        defaultConfig.setModelVersion("v1.0");
        defaultConfig.setConfidenceThreshold(0.8);
        defaultConfig.setMaxRetries(3);
        defaultConfig.setTimeoutMs(5000);
        return defaultConfig;
    }

    public void updateConfig(String analysisType, AIAnalysisConfig config) {
        configMap.put(analysisType, config);
    }
}