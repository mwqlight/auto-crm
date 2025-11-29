package com.stellarcrm.repository;

import com.stellarcrm.domain.service.Ticket;
import com.stellarcrm.domain.service.TicketPriority;
import com.stellarcrm.domain.service.TicketStatus;
import com.stellarcrm.domain.service.TicketCategory;
import com.stellarcrm.domain.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCustomer(Customer customer);
    
    List<Ticket> findByStatus(TicketStatus status);
    
    List<Ticket> findByPriority(TicketPriority priority);
    
    List<Ticket> findByCategory(TicketCategory category);
    
    List<Ticket> findByAssignedTo(String assignedTo);
    
    List<Ticket> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT t FROM Ticket t WHERE t.title LIKE %:keyword% OR t.description LIKE %:keyword%")
    List<Ticket> searchByTitleOrDescription(@Param("keyword") String keyword);
    
    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.status = :status")
    Long countByStatus(@Param("status") TicketStatus status);
}