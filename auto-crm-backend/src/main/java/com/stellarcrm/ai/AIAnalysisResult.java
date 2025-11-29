package com.stellarcrm.ai;

import com.stellarcrm.domain.customer.Customer;
import com.stellarcrm.domain.sales.Opportunity;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ai_analysis_results")
@Data
public class AIAnalysisResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "entity_id")
    private Long entityId;
    
    @Column(name = "entity_type")
    private String entityType;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "opportunity_id")
    private Opportunity opportunity;
    
    @Column(name = "analysis_type")
    private String analysisType; // 销售预测、风险评估等
    
    @Column(name = "confidence_score")
    private Double confidenceScore;
    
    @Column(name = "prediction")
    private String prediction;
    
    @Column(name = "predicted_value")
    private BigDecimal predictedValue;
    
    @Column(name = "risk_level")
    private String riskLevel; // LOW, MEDIUM, HIGH
    
    @Column(name = "recommendations", columnDefinition = "TEXT")
    private String recommendations;
    
    @Column(name = "analysis_data", columnDefinition = "JSON")
    private String analysisData;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}