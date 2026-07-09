package com.satya.CRM.service;

import com.satya.CRM.entity.LeadType;
import com.satya.CRM.repository.LeadTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LeadTypeService {


    private final LeadTypeRepository leadTypeRepository;

    public LeadTypeService(LeadTypeRepository leadTypeRepository) {
        this.leadTypeRepository = leadTypeRepository;
    }

    public List<LeadType> getAllLeadTypes() {
        return leadTypeRepository.findAll();
    }

    public LeadType getLeadTypeById(Long id) {
        return leadTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LeadType not found"));
    }

    public LeadType createLeadType(LeadType leadType) {
        return leadTypeRepository.save(leadType);
    }

    public LeadType updateLeadType(Long id, LeadType leadTypeDetails) {
        LeadType leadType = leadTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LeadType not found"));
        leadType.setName(leadTypeDetails.getName());
        leadType.setDescription(leadTypeDetails.getDescription());
        return leadTypeRepository.save(leadType);
    }

    public void deleteLeadType(Long id) {
        LeadType leadType = leadTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LeadType not found"));
        leadTypeRepository.delete(leadType);
    }
}
