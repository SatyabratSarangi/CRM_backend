package com.satya.CRM.controller;

import com.satya.CRM.entity.CustomerLead;
import com.satya.CRM.service.CustomerLeadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
public class CustomerLeadController {


    private final CustomerLeadService customerLeadService;

    public CustomerLeadController(CustomerLeadService customerLeadService) {
        this.customerLeadService = customerLeadService;
    }

    @GetMapping
    public List<CustomerLead> getAllLeads() {
        return customerLeadService.getAllLeads();
    }

    @GetMapping("/search")
    public List<CustomerLead> searchLeads(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) String city) {
        return customerLeadService.searchLeads(name, status, priority, city);
    }

    @GetMapping("/{id}")
    public CustomerLead getLeadById(@PathVariable Long id) {
        return customerLeadService.getLeadById(id);
    }

    @PostMapping
    public CustomerLead createLead(@Valid @RequestBody CustomerLead lead) {
        return customerLeadService.createLead(lead);
    }

    @PutMapping("/{id}")
    public CustomerLead updateLead(@PathVariable Long id, @Valid @RequestBody CustomerLead lead) {
        return customerLeadService.updateLead(id, lead);
    }

    @DeleteMapping("/{id}")
    public void deleteLead(@PathVariable Long id) {
        customerLeadService.deleteLead(id);
    }
}
