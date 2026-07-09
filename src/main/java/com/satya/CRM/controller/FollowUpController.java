package com.satya.CRM.controller;

import com.satya.CRM.entity.FollowUp;
import com.satya.CRM.service.FollowUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/leads/{leadId}/followups")
public class FollowUpController {

    private final FollowUpService followUpService;

    public FollowUpController(FollowUpService followUpService) {
        this.followUpService = followUpService;
    }

    @GetMapping
    public List<FollowUp> getFollowUps(@PathVariable Long leadId) {
        return followUpService.getFollowUpsByLeadId(leadId);
    }

    @PostMapping
    public FollowUp createFollowUp(@PathVariable Long leadId, @Valid @RequestBody FollowUp followUp) {
        return followUpService.createFollowUp(leadId, followUp);
    }
}
