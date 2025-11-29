package com.stellarcrm.service;

import com.stellarcrm.domain.marketing.CampaignStatus;
import com.stellarcrm.domain.marketing.CampaignType;
import com.stellarcrm.dto.marketing.CampaignCreateRequest;
import com.stellarcrm.dto.marketing.CampaignResponse;
import com.stellarcrm.dto.marketing.CampaignUpdateRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface MarketingService {
    CampaignResponse createCampaign(CampaignCreateRequest request);
    
    CampaignResponse getCampaignById(Long id);
    
    List<CampaignResponse> getAllCampaigns();
    
    CampaignResponse updateCampaign(Long id, CampaignUpdateRequest request);
    
    void deleteCampaign(Long id);
    
    List<CampaignResponse> getCampaignsByStatus(CampaignStatus status);
    
    List<CampaignResponse> getCampaignsByType(CampaignType type);
    
    List<CampaignResponse> searchCampaigns(String keyword);
    
    List<CampaignResponse> getCampaignsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
}