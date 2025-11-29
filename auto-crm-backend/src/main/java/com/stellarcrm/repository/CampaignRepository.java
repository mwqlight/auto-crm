package com.stellarcrm.repository;

import com.stellarcrm.domain.marketing.Campaign;
import com.stellarcrm.domain.marketing.CampaignStatus;
import com.stellarcrm.domain.marketing.CampaignType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findByStatus(CampaignStatus status);
    
    List<Campaign> findByType(CampaignType type);
    
    List<Campaign> findByStartDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    List<Campaign> findByEndDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    @Query("SELECT c FROM Campaign c WHERE c.name LIKE %:keyword% OR c.description LIKE %:keyword%")
    List<Campaign> searchByNameOrDescription(@Param("keyword") String keyword);
}