package com.stellarcrm.domain.customer;

public enum CustomerStatus {
    LEAD,           // 潜在客户
    PROSPECT,       // 目标客户
    CUSTOMER,       // 正式客户
    CHURNED,        // 流失客户
    INACTIVE        // 不活跃客户
}