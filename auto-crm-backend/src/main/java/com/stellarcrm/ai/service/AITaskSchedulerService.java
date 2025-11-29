package com.stellarcrm.ai.service;

import com.stellarcrm.ai.dto.AIAnalysisRequest;
import com.stellarcrm.ai.dto.AIAnalysisResponse;
import com.stellarcrm.ai.enums.EntityType;
import com.stellarcrm.ai.enums.AnalysisType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Service
public class AITaskSchedulerService {

    private static final Logger logger = Logger.getLogger(AITaskSchedulerService.class.getName());

    @Autowired
    private AIAnalysisService aiAnalysisService;

    /**
     * 定时执行客户风险评估任务
     * 每天凌晨2点执行
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void scheduleCustomerRiskAssessment() {
        logger.info("开始执行客户风险评估任务");

        // 这里应该从数据库获取需要评估的客户列表
        // 为演示目的，我们创建一个示例请求
        AIAnalysisRequest request = new AIAnalysisRequest();
        request.setEntityId(1L); // 示例客户ID
        request.setEntityType(EntityType.CUSTOMER);
        request.setAnalysisType(AnalysisType.CUSTOMER_RISK_ASSESSMENT);

        // 执行分析
        CompletableFuture<AIAnalysisResponse> future = aiAnalysisService.analyze(request);

        // 处理结果
        future.thenAccept(response -> {
            if (response != null) {
                logger.info("客户风险评估完成，客户ID: " + response.getEntityId() + 
                           ", 风险等级: " + response.getRiskLevel());
            } else {
                logger.warning("客户风险评估失败");
            }
        }).exceptionally(ex -> {
            logger.severe("客户风险评估过程中发生异常: " + ex.getMessage());
            return null;
        });

        logger.info("客户风险评估任务已提交");
    }

    /**
     * 定时执行销售机会预测任务
     * 每天上午10点执行
     */
    @Scheduled(cron = "0 0 10 * * ?")
    public void scheduleSalesOpportunityPrediction() {
        logger.info("开始执行销售机会预测任务");

        // 这里应该从数据库获取需要预测的销售机会列表
        // 为演示目的，我们创建一个示例请求
        AIAnalysisRequest request = new AIAnalysisRequest();
        request.setEntityId(1L); // 示例机会ID
        request.setEntityType(EntityType.OPPORTUNITY);
        request.setAnalysisType(AnalysisType.SALES_PREDICTION);

        // 执行分析
        CompletableFuture<AIAnalysisResponse> future = aiAnalysisService.analyze(request);

        // 处理结果
        future.thenAccept(response -> {
            if (response != null) {
                logger.info("销售机会预测完成，机会ID: " + response.getEntityId() + 
                           ", 预测结果: " + response.getPrediction());
            } else {
                logger.warning("销售机会预测失败");
            }
        }).exceptionally(ex -> {
            logger.severe("销售机会预测过程中发生异常: " + ex.getMessage());
            return null;
        });

        logger.info("销售机会预测任务已提交");
    }
}