package com.stellarcrm.service.impl;

import com.stellarcrm.domain.marketing.Campaign;
import com.stellarcrm.domain.marketing.CampaignStatus;
import com.stellarcrm.domain.marketing.CampaignType;
import com.stellarcrm.dto.marketing.CampaignCreateRequest;
import com.stellarcrm.dto.marketing.CampaignResponse;
import com.stellarcrm.dto.marketing.CampaignUpdateRequest;
import com.stellarcrm.repository.CampaignRepository;
import com.stellarcrm.service.MarketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarketingServiceImpl implements MarketingService {
    
    @Autowired
    private CampaignRepository campaignRepository;
    
    @Override
    public CampaignResponse createCampaign(CampaignCreateRequest request) {
        Campaign campaign = new Campaign();
        campaign.setName(request.getName());
        campaign.setDescription(request.getDescription());
        campaign.setType(request.getType());
        campaign.setStatus(request.getStatus());
        campaign.setStartDate(request.getStartDate());
        campaign.setEndDate(request.getEndDate());
        campaign.setBudget(request.getBudget());
        campaign.setCreatedAt(LocalDateTime.now());
        campaign.setUpdatedAt(LocalDateTime.now());
        
        Campaign savedCampaign = campaignRepository.save(campaign);
        return convertToResponse(savedCampaign);
    }
    
    @Override
    public CampaignResponse getCampaignById(Long id) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found with id: " + id));
        return convertToResponse(campaign);
    }
    
    @Override
    public List<CampaignResponse> getAllCampaigns() {
        return campaignRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public CampaignResponse updateCampaign(Long id, CampaignUpdateRequest request) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found with id: " + id));
        
        campaign.setName(request.getName());
        campaign.setDescription(request.getDescription());
        campaign.setType(request.getType());
        campaign.setStatus(request.getStatus());
        campaign.setStartDate(request.getStartDate());
        campaign.setEndDate(request.getEndDate());
        campaign.setBudget(request.getBudget());
        campaign.setSpent(request.getSpent());
        campaign.setExpectedLeads(request.getExpectedLeads());
        campaign.setActualLeads(request.getActualLeads());
        campaign.setUpdatedAt(LocalDateTime.now());
        
        Campaign updatedCampaign = campaignRepository.save(campaign);
        return convertToResponse(updatedCampaign);
    }
    
    @Override
    public void deleteCampaign(Long id) {
        if (!campaignRepository.existsById(id)) {
            throw new RuntimeException("Campaign not found with id: " + id);
        }
        campaignRepository.deleteById(id);
    }
    
    @Override
    public List<CampaignResponse> getCampaignsByStatus(CampaignStatus status) {
        return campaignRepository.findByStatus(status).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<CampaignResponse> getCampaignsByType(CampaignType type) {
        return campaignRepository.findByType(type).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<CampaignResponse> searchCampaigns(String keyword) {
        return campaignRepository.searchByNameOrDescription(keyword).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<CampaignResponse> getCampaignsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        // 这里我们查找在指定日期范围内开始或结束的活动
        return campaignRepository.findByStartDateBetween(startDate, endDate).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    private CampaignResponse convertToResponse(Campaign campaign) {
        CampaignResponse response = new CampaignResponse();
        response.setId(campaign.getId());
        response.setName(campaign.getName());
        response.setDescription(campaign.getDescription());
        response.setType(campaign.getType());
        response.setStatus(campaign.getStatus());
        response.setStartDate(campaign.getStartDate());
        response.setEndDate(campaign.getEndDate());
        response.setBudget(campaign.getBudget());
        response.setSpent(campaign.getSpent());
        response.setExpectedLeads(campaign.getExpectedLeads());
        response.setActualLeads(campaign.getActualLeads());
        response.setCreatedAt(campaign.getCreatedAt());
        response.setUpdatedAt(campaign.getUpdatedAt());
        return response;
    }
}