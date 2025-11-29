package com.stellarcrm.controller;

import com.stellarcrm.domain.sales.OpportunityStage;
import com.stellarcrm.dto.sales.OpportunityCreateRequest;
import com.stellarcrm.dto.sales.OpportunityResponse;
import com.stellarcrm.dto.sales.OpportunityUpdateRequest;
import com.stellarcrm.service.SalesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/opportunities")
@Tag(name = "销售管理", description = "销售机会相关API")
public class SalesController {
    
    @Autowired
    private SalesService salesService;
    
    @PostMapping
    @Operation(summary = "创建销售机会")
    public ResponseEntity<OpportunityResponse> createOpportunity(@RequestBody OpportunityCreateRequest request) {
        OpportunityResponse response = salesService.createOpportunity(request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取销售机会")
    public ResponseEntity<OpportunityResponse> getOpportunityById(@PathVariable Long id) {
        OpportunityResponse response = salesService.getOpportunityById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @Operation(summary = "获取所有销售机会")
    public ResponseEntity<List<OpportunityResponse>> getAllOpportunities() {
        List<OpportunityResponse> opportunities = salesService.getAllOpportunities();
        return ResponseEntity.ok(opportunities);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "更新销售机会")
    public ResponseEntity<OpportunityResponse> updateOpportunity(
            @PathVariable Long id,
            @RequestBody OpportunityUpdateRequest request) {
        OpportunityResponse response = salesService.updateOpportunity(id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "删除销售机会")
    public ResponseEntity<Void> deleteOpportunity(@PathVariable Long id) {
        salesService.deleteOpportunity(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/stage/{stage}")
    @Operation(summary = "根据阶段获取销售机会")
    public ResponseEntity<List<OpportunityResponse>> getOpportunitiesByStage(@PathVariable OpportunityStage stage) {
        List<OpportunityResponse> opportunities = salesService.getOpportunitiesByStage(stage);
        return ResponseEntity.ok(opportunities);
    }
    
    @GetMapping("/value-range")
    @Operation(summary = "根据价值范围获取销售机会")
    public ResponseEntity<List<OpportunityResponse>> getOpportunitiesByValueRange(
            @RequestParam BigDecimal minValue,
            @RequestParam BigDecimal maxValue) {
        List<OpportunityResponse> opportunities = salesService.getOpportunitiesByValueRange(minValue, maxValue);
        return ResponseEntity.ok(opportunities);
    }
    
    @GetMapping("/close-date-range")
    @Operation(summary = "根据预计关闭日期范围获取销售机会")
    public ResponseEntity<List<OpportunityResponse>> getOpportunitiesByCloseDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<OpportunityResponse> opportunities = salesService.getOpportunitiesByCloseDateRange(startDate, endDate);
        return ResponseEntity.ok(opportunities);
    }
    
    @GetMapping("/total-value/{stage}")
    @Operation(summary = "获取指定阶段的销售机会总价值")
    public ResponseEntity<BigDecimal> getTotalValueByStage(@PathVariable OpportunityStage stage) {
        BigDecimal totalValue = salesService.getTotalValueByStage(stage);
        return ResponseEntity.ok(totalValue);
    }
}