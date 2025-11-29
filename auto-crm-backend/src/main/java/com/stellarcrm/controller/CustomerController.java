package com.stellarcrm.controller;

import com.stellarcrm.domain.customer.CustomerStatus;
import com.stellarcrm.domain.customer.CustomerTier;
import com.stellarcrm.dto.customer.CustomerCreateRequest;
import com.stellarcrm.dto.customer.CustomerResponse;
import com.stellarcrm.dto.customer.CustomerUpdateRequest;
import com.stellarcrm.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@Tag(name = "客户管理", description = "客户相关API")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    @PostMapping
    @PreAuthorize("hasAuthority('customer:create')")
    @Operation(summary = "创建客户")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerCreateRequest request) {
        CustomerResponse response = customerService.createCustomer(request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('customer:read')")
    @Operation(summary = "根据ID获取客户")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        CustomerResponse response = customerService.getCustomerById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @PreAuthorize("hasAuthority('customer:read')")
    @Operation(summary = "获取所有客户")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('customer:update')")
    @Operation(summary = "更新客户信息")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerUpdateRequest request) {
        CustomerResponse response = customerService.updateCustomer(id, request);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('customer:delete')")
    @Operation(summary = "删除客户")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/search")
    @PreAuthorize("hasAuthority('customer:read')")
    @Operation(summary = "搜索客户")
    public ResponseEntity<List<CustomerResponse>> searchCustomers(@RequestParam String keyword) {
        List<CustomerResponse> customers = customerService.searchCustomers(keyword);
        return ResponseEntity.ok(customers);
    }
    
    @GetMapping("/status/{status}")
    @PreAuthorize("hasAuthority('customer:read')")
    @Operation(summary = "根据状态获取客户")
    public ResponseEntity<List<CustomerResponse>> getCustomersByStatus(@PathVariable CustomerStatus status) {
        List<CustomerResponse> customers = customerService.getCustomersByStatus(status);
        return ResponseEntity.ok(customers);
    }
    
    @GetMapping("/tier/{tier}")
    @PreAuthorize("hasAuthority('customer:read')")
    @Operation(summary = "根据等级获取客户")
    public ResponseEntity<List<CustomerResponse>> getCustomersByTier(@PathVariable CustomerTier tier) {
        List<CustomerResponse> customers = customerService.getCustomersByTier(tier);
        return ResponseEntity.ok(customers);
    }
}