package com.stellarcrm.dto.service;

import com.stellarcrm.dto.customer.CustomerResponse;
import com.stellarcrm.domain.service.TicketPriority;
import com.stellarcrm.domain.service.TicketStatus;
import com.stellarcrm.domain.service.TicketCategory;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketResponse {
    private Long id;
    private String title;
    private String description;
    private CustomerResponse customer;
    private TicketPriority priority;
    private TicketStatus status;
    private TicketCategory category;
    private String assignedTo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime resolvedAt;
}