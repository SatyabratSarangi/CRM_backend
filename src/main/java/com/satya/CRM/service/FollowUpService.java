package com.satya.CRM.service;

import com.satya.CRM.entity.CustomerLead;
import com.satya.CRM.entity.FollowUp;
import com.satya.CRM.repository.CustomerLeadRepository;
import com.satya.CRM.repository.FollowUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FollowUpService {

    private final FollowUpRepository followUpRepository;

    private final CustomerLeadRepository customerLeadRepository;

    public FollowUpService(FollowUpRepository followUpRepository, CustomerLeadRepository customerLeadRepository) {
        this.followUpRepository = followUpRepository;
        this.customerLeadRepository = customerLeadRepository;
    }


    public List<FollowUp> getFollowUpsByLeadId(Long leadId) {
        return followUpRepository.findByLeadIdOrderByFollowUpDateDesc(leadId);
    }

    public FollowUp createFollowUp(Long leadId, FollowUp followUp) {
        CustomerLead lead = customerLeadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Lead not found"));
        
        followUp.setLead(lead);
        
        // Update the lead's status and next followup date if provided
        if (followUp.getStatus() != null && !followUp.getStatus().isEmpty()) {
            lead.setStatus(followUp.getStatus());
        }
        if (followUp.getNextFollowUpDate() != null) {
            lead.setNextFollowUpDate(followUp.getNextFollowUpDate());
        }
        customerLeadRepository.save(lead);

        return followUpRepository.save(followUp);
    }
}
