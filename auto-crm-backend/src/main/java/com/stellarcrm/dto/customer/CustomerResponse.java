package com.stellarcrm.dto.customer;

import com.stellarcrm.domain.customer.CustomerStatus;
import com.stellarcrm.domain.customer.CustomerTier;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CustomerResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String company;
    private String industry;
    private String address;
    private CustomerStatus status;
    private CustomerTier tier;
    private Double lifetimeValue;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}