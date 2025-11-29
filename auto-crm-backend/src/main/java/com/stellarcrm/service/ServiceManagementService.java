package com.stellarcrm.service;

import com.stellarcrm.dto.service.TicketCreateRequest;
import com.stellarcrm.dto.service.TicketUpdateRequest;
import com.stellarcrm.dto.service.TicketResponse;
import com.stellarcrm.domain.service.TicketPriority;
import com.stellarcrm.domain.service.TicketStatus;
import com.stellarcrm.domain.service.TicketCategory;

import java.time.LocalDateTime;
import java.util.List;

public interface ServiceManagementService {
    TicketResponse createTicket(TicketCreateRequest request);
    
    TicketResponse getTicketById(Long id);
    
    List<TicketResponse> getAllTickets();
    
    TicketResponse updateTicket(Long id, TicketUpdateRequest request);
    
    void deleteTicket(Long id);
    
    List<TicketResponse> getTicketsByCustomer(Long customerId);
    
    List<TicketResponse> getTicketsByStatus(TicketStatus status);
    
    List<TicketResponse> getTicketsByPriority(TicketPriority priority);
    
    List<TicketResponse> getTicketsByCategory(TicketCategory category);
    
    List<TicketResponse> getTicketsByAssignedTo(String assignedTo);
    
    List<TicketResponse> getTicketsByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    
    List<TicketResponse> searchTickets(String keyword);
    
    Long getTicketCountByStatus(TicketStatus status);
}