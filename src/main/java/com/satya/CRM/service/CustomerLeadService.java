package com.satya.CRM.service;

import com.satya.CRM.entity.CustomerLead;
import com.satya.CRM.repository.CustomerLeadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerLeadService {

    private final CustomerLeadRepository customerLeadRepository;

    public CustomerLeadService(CustomerLeadRepository customerLeadRepository) {
        this.customerLeadRepository = customerLeadRepository;
    }

    public List<CustomerLead> getAllLeads() {
        return customerLeadRepository.findAll();
    }

    public CustomerLead getLeadById(Long id) {
        return customerLeadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lead not found"));
    }

    public CustomerLead createLead(CustomerLead lead) {
        return customerLeadRepository.save(lead);
    }

    public CustomerLead updateLead(Long id, CustomerLead leadDetails) {
        CustomerLead lead =customerLeadRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Lead not found"));
        lead.setCustomerName(leadDetails.getCustomerName());
        lead.setMobile(leadDetails.getMobile());
        lead.setAlternateNumber(leadDetails.getAlternateNumber());
        lead.setEmail(leadDetails.getEmail());
        lead.setLeadType(leadDetails.getLeadType());
        lead.setCity(leadDetails.getCity());
        lead.setAddress(leadDetails.getAddress());
        lead.setRequirement(leadDetails.getRequirement());
        lead.setLeadSource(leadDetails.getLeadSource());
        lead.setAssignedExecutive(leadDetails.getAssignedExecutive());
        lead.setDiscussionDetails(leadDetails.getDiscussionDetails());
        lead.setVisitDate(leadDetails.getVisitDate());
        lead.setNextFollowUpDate(leadDetails.getNextFollowUpDate());
        lead.setStatus(leadDetails.getStatus());
        lead.setPriority(leadDetails.getPriority());
        return customerLeadRepository.save(lead);
    }

    public void deleteLead(Long id) {
        CustomerLead lead =customerLeadRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Lead not found"));
        customerLeadRepository.delete(lead);
    }


    public List<CustomerLead> searchLeads(String name, String status, String priority, String city) {
        List<CustomerLead> allLeads = customerLeadRepository.findAll();

        return allLeads.stream()
                // Filter by Name
                .filter(lead -> name == null || name.isEmpty() ||
                        (lead.getCustomerName() != null && lead.getCustomerName().toLowerCase().contains(name.toLowerCase())))
                // Filter by Status
                .filter(lead -> status == null || status.isEmpty() ||
                        (lead.getStatus() != null && lead.getStatus().equals(status)))
                // Filter by Priority
                .filter(lead -> priority == null || priority.isEmpty() ||
                        (lead.getPriority() != null && lead.getPriority().equals(priority)))
                // Filter by City
                .filter(lead -> city == null || city.isEmpty() ||
                        (lead.getCity() != null && lead.getCity().toLowerCase().contains(city.toLowerCase())))
                // Collect the results into a new List
                .collect(Collectors.toList());
    }
}
