package com.stellarcrm.controller;

import com.stellarcrm.service.ServiceManagementService;
import com.stellarcrm.dto.service.TicketCreateRequest;
import com.stellarcrm.dto.service.TicketUpdateRequest;
import com.stellarcrm.dto.service.TicketResponse;
import com.stellarcrm.domain.service.TicketPriority;
import com.stellarcrm.domain.service.TicketStatus;
import com.stellarcrm.domain.service.TicketCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class ServiceManagementController {
    
    @Autowired
    private ServiceManagementService serviceManagementService;
    
    @PostMapping
    public TicketResponse createTicket(@RequestBody TicketCreateRequest request) {
        return serviceManagementService.createTicket(request);
    }
    
    @GetMapping("/{id}")
    public TicketResponse getTicketById(@PathVariable Long id) {
        return serviceManagementService.getTicketById(id);
    }
    
    @GetMapping
    public List<TicketResponse> getAllTickets() {
        return serviceManagementService.getAllTickets();
    }
    
    @PutMapping("/{id}")
    public TicketResponse updateTicket(@PathVariable Long id, @RequestBody TicketUpdateRequest request) {
        return serviceManagementService.updateTicket(id, request);
    }
    
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        serviceManagementService.deleteTicket(id);
    }
    
    @GetMapping("/customer/{customerId}")
    public List<TicketResponse> getTicketsByCustomer(@PathVariable Long customerId) {
        return serviceManagementService.getTicketsByCustomer(customerId);
    }
    
    @GetMapping("/status/{status}")
    public List<TicketResponse> getTicketsByStatus(@PathVariable TicketStatus status) {
        return serviceManagementService.getTicketsByStatus(status);
    }
    
    @GetMapping("/priority/{priority}")
    public List<TicketResponse> getTicketsByPriority(@PathVariable TicketPriority priority) {
        return serviceManagementService.getTicketsByPriority(priority);
    }
    
    @GetMapping("/category/{category}")
    public List<TicketResponse> getTicketsByCategory(@PathVariable TicketCategory category) {
        return serviceManagementService.getTicketsByCategory(category);
    }
    
    @GetMapping("/assigned/{assignedTo}")
    public List<TicketResponse> getTicketsByAssignedTo(@PathVariable String assignedTo) {
        return serviceManagementService.getTicketsByAssignedTo(assignedTo);
    }
    
    @GetMapping("/date-range")
    public List<TicketResponse> getTicketsByDateRange(
            @RequestParam LocalDateTime startDate, 
            @RequestParam LocalDateTime endDate) {
        return serviceManagementService.getTicketsByDateRange(startDate, endDate);
    }
    
    @GetMapping("/search")
    public List<TicketResponse> searchTickets(@RequestParam String keyword) {
        return serviceManagementService.searchTickets(keyword);
    }
    
    @GetMapping("/count/status/{status}")
    public Long getTicketCountByStatus(@PathVariable TicketStatus status) {
        return serviceManagementService.getTicketCountByStatus(status);
    }
}