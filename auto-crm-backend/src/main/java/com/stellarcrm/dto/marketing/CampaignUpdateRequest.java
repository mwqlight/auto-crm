package com.stellarcrm.dto.marketing;

import com.stellarcrm.domain.marketing.CampaignStatus;
import com.stellarcrm.domain.marketing.CampaignType;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CampaignUpdateRequest {
    private String name;
    private String description;
    private CampaignType type;
    private CampaignStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double budget;
    private Double spent;
    private Integer expectedLeads;
    private Integer actualLeads;
}