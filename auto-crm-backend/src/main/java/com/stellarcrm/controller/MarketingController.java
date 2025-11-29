package com.stellarcrm.controller;

import com.stellarcrm.domain.marketing.CampaignStatus;
import com.stellarcrm.domain.marketing.CampaignType;
import com.stellarcrm.dto.marketing.CampaignCreateRequest;
import com.stellarcrm.dto.marketing.CampaignResponse;
import com.stellarcrm.dto.marketing.CampaignUpdateRequest;
import com.stellarcrm.service.MarketingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/campaigns")
@Tag(name = "营销管理", description = "营销活动相关API")
public class MarketingController {
    
    @Autowired
    private MarketingService marketingService;
    
    @PostMapping
    @Operation(summary = "创建营销活动")
    public ResponseEntity<CampaignResponse> createCampaign(@RequestBody CampaignCreateRequest request) {
        CampaignResponse response = marketingService.createCampaign(request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取营销活动")
    public ResponseEntity<CampaignResponse> getCampaignById(@PathVariable Long id) {
        CampaignResponse response = marketingService.getCampaignById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @Operation(summary = "获取所有营销活动")
    public ResponseEntity<List<CampaignResponse>> getAllCampaigns() {
        List<CampaignResponse> campaigns = marketingService.getAllCampaigns();
        return ResponseEntity.ok(campaigns);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "更新营销活动")
    public ResponseEntity<CampaignResponse> updateCampaign(
            @PathVariable Long id,
            @RequestBody CampaignUpdateRequest request) {
        CampaignResponse response = marketingService.updateCampaign(id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "删除营销活动")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
        marketingService.deleteCampaign(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/status/{status}")
    @Operation(summary = "根据状态获取营销活动")
    public ResponseEntity<List<CampaignResponse>> getCampaignsByStatus(@PathVariable CampaignStatus status) {
        List<CampaignResponse> campaigns = marketingService.getCampaignsByStatus(status);
        return ResponseEntity.ok(campaigns);
    }
    
    @GetMapping("/type/{type}")
    @Operation(summary = "根据类型获取营销活动")
    public ResponseEntity<List<CampaignResponse>> getCampaignsByType(@PathVariable CampaignType type) {
        List<CampaignResponse> campaigns = marketingService.getCampaignsByType(type);
        return ResponseEntity.ok(campaigns);
    }
    
    @GetMapping("/search")
    @Operation(summary = "搜索营销活动")
    public ResponseEntity<List<CampaignResponse>> searchCampaigns(@RequestParam String keyword) {
        List<CampaignResponse> campaigns = marketingService.searchCampaigns(keyword);
        return ResponseEntity.ok(campaigns);
    }
    
    @GetMapping("/date-range")
    @Operation(summary = "根据日期范围获取营销活动")
    public ResponseEntity<List<CampaignResponse>> getCampaignsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<CampaignResponse> campaigns = marketingService.getCampaignsByDateRange(startDate, endDate);
        return ResponseEntity.ok(campaigns);
    }
}