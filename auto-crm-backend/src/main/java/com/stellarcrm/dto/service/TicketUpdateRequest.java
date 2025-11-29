package com.stellarcrm.dto.service;

import com.stellarcrm.domain.service.TicketPriority;
import com.stellarcrm.domain.service.TicketStatus;
import com.stellarcrm.domain.service.TicketCategory;
import lombok.Data;

@Data
public class TicketUpdateRequest {
    private String title;
    private String description;
    private Long customerId;
    private TicketPriority priority;
    private TicketStatus status;
    private TicketCategory category;
    private String assignedTo;
}