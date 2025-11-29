package com.stellarcrm.service.impl;

import com.stellarcrm.service.ServiceManagementService;
import com.stellarcrm.dto.service.TicketCreateRequest;
import com.stellarcrm.dto.service.TicketUpdateRequest;
import com.stellarcrm.dto.service.TicketResponse;
import com.stellarcrm.domain.service.Ticket;
import com.stellarcrm.domain.service.TicketPriority;
import com.stellarcrm.domain.service.TicketStatus;
import com.stellarcrm.domain.service.TicketCategory;
import com.stellarcrm.domain.customer.Customer;
import com.stellarcrm.repository.TicketRepository;
import com.stellarcrm.repository.CustomerRepository;
import com.stellarcrm.dto.customer.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceManagementServiceImpl implements ServiceManagementService {
    
    @Autowired
    private TicketRepository ticketRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public TicketResponse createTicket(TicketCreateRequest request) {
        Ticket ticket = new Ticket();
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        
        if (request.getCustomerId() != null) {
            Customer customer = customerRepository.findById(request.getCustomerId()).orElse(null);
            ticket.setCustomer(customer);
        }
        
        ticket.setPriority(request.getPriority());
        ticket.setCategory(request.getCategory());
        ticket.setAssignedTo(request.getAssignedTo());
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setCreatedAt(LocalDateTime.now());
        
        Ticket savedTicket = ticketRepository.save(ticket);
        return convertToResponse(savedTicket);
    }
    
    @Override
    public TicketResponse getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        return ticket != null ? convertToResponse(ticket) : null;
    }
    
    @Override
    public List<TicketResponse> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public TicketResponse updateTicket(Long id, TicketUpdateRequest request) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if (ticket == null) {
            return null;
        }
        
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        
        if (request.getCustomerId() != null) {
            Customer customer = customerRepository.findById(request.getCustomerId()).orElse(null);
            ticket.setCustomer(customer);
        }
        
        ticket.setPriority(request.getPriority());
        ticket.setStatus(request.getStatus());
        ticket.setCategory(request.getCategory());
        ticket.setAssignedTo(request.getAssignedTo());
        ticket.setUpdatedAt(LocalDateTime.now());
        
        if (request.getStatus() == TicketStatus.RESOLVED && ticket.getResolvedAt() == null) {
            ticket.setResolvedAt(LocalDateTime.now());
        }
        
        Ticket updatedTicket = ticketRepository.save(ticket);
        return convertToResponse(updatedTicket);
    }
    
    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
    
    @Override
    public List<TicketResponse> getTicketsByCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) {
            return List.of();
        }
        return ticketRepository.findByCustomer(customer).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<TicketResponse> getTicketsByStatus(TicketStatus status) {
        return ticketRepository.findByStatus(status).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<TicketResponse> getTicketsByPriority(TicketPriority priority) {
        return ticketRepository.findByPriority(priority).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<TicketResponse> getTicketsByCategory(TicketCategory category) {
        return ticketRepository.findByCategory(category).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<TicketResponse> getTicketsByAssignedTo(String assignedTo) {
        return ticketRepository.findByAssignedTo(assignedTo).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<TicketResponse> getTicketsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return ticketRepository.findByCreatedAtBetween(startDate, endDate).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<TicketResponse> searchTickets(String keyword) {
        return ticketRepository.searchByTitleOrDescription(keyword).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public Long getTicketCountByStatus(TicketStatus status) {
        return ticketRepository.countByStatus(status);
    }
    
    private TicketResponse convertToResponse(Ticket ticket) {
        TicketResponse response = new TicketResponse();
        response.setId(ticket.getId());
        response.setTitle(ticket.getTitle());
        response.setDescription(ticket.getDescription());
        
        if (ticket.getCustomer() != null) {
            CustomerResponse customerResponse = new CustomerResponse();
            customerResponse.setId(ticket.getCustomer().getId());
            customerResponse.setName(ticket.getCustomer().getName());
            customerResponse.setEmail(ticket.getCustomer().getEmail());
            customerResponse.setPhone(ticket.getCustomer().getPhone());
            customerResponse.setCompany(ticket.getCustomer().getCompany());
            customerResponse.setAddress(ticket.getCustomer().getAddress());
            customerResponse.setIndustry(ticket.getCustomer().getIndustry());
            customerResponse.setCreatedAt(ticket.getCustomer().getCreatedAt());
            customerResponse.setUpdatedAt(ticket.getCustomer().getUpdatedAt());
            response.setCustomer(customerResponse);
        }
        
        response.setPriority(ticket.getPriority());
        response.setStatus(ticket.getStatus());
        response.setCategory(ticket.getCategory());
        response.setAssignedTo(ticket.getAssignedTo());
        response.setCreatedAt(ticket.getCreatedAt());
        response.setUpdatedAt(ticket.getUpdatedAt());
        response.setResolvedAt(ticket.getResolvedAt());
        
        return response;
    }
}