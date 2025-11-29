package com.stellarcrm.dto.service;

import com.stellarcrm.domain.service.TicketPriority;
import com.stellarcrm.domain.service.TicketCategory;
import lombok.Data;

@Data
public class TicketCreateRequest {
    private String title;
    private String description;
    private Long customerId;
    private TicketPriority priority;
    private TicketCategory category;
    private String assignedTo;
}