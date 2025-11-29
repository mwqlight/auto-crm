package com.stellarcrm.dto.customer;

import com.stellarcrm.domain.customer.CustomerStatus;
import com.stellarcrm.domain.customer.CustomerTier;
import lombok.Data;

@Data
public class CustomerUpdateRequest {
    private String name;
    private String email;
    private String phone;
    private String company;
    private String industry;
    private String address;
    private CustomerStatus status;
    private CustomerTier tier;
    private Double lifetimeValue;
}