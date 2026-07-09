package com.satya.CRM.controller;

import com.satya.CRM.entity.LeadType;
import com.satya.CRM.service.LeadTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/lead-types")
public class LeadTypeController {

    private final LeadTypeService leadTypeService;

    public LeadTypeController(LeadTypeService leadTypeService) {
        this.leadTypeService = leadTypeService;
    }

    @GetMapping
    public List<LeadType> getAllLeadTypes() {
        return leadTypeService.getAllLeadTypes();
    }

    @GetMapping("/{id}")
    public LeadType getLeadTypeById(@PathVariable Long id) {
        return leadTypeService.getLeadTypeById(id);
    }

    @PostMapping
    public LeadType createLeadType(@Valid @RequestBody LeadType leadType) {
        return leadTypeService.createLeadType(leadType);
    }

    @PutMapping("/{id}")
    public LeadType updateLeadType(@PathVariable Long id, @Valid @RequestBody LeadType leadType) {
        return leadTypeService.updateLeadType(id, leadType);
    }

    @DeleteMapping("/{id}")
    public void deleteLeadType(@PathVariable Long id) {
        leadTypeService.deleteLeadType(id);
    }
}
