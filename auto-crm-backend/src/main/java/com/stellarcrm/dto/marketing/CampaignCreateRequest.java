package com.stellarcrm.dto.marketing;

import com.stellarcrm.domain.marketing.CampaignStatus;
import com.stellarcrm.domain.marketing.CampaignType;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CampaignCreateRequest {
    private String name;
    private String description;
    private CampaignType type;
    private CampaignStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double budget;
}