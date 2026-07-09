package com.satya.CRM.service;

import com.satya.CRM.dto.DashboardStatsDto;
import com.satya.CRM.repository.CustomerLeadRepository;
import com.satya.CRM.repository.FollowUpRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DashboardService {


    private final CustomerLeadRepository customerLeadRepository;


    private final FollowUpRepository followUpRepository;

    public DashboardService(CustomerLeadRepository customerLeadRepository, FollowUpRepository followUpRepository) {
        this.customerLeadRepository = customerLeadRepository;
        this.followUpRepository = followUpRepository;
    }

    public DashboardStatsDto getDashboardStats() {
        long totalLeads = customerLeadRepository.count();
        long todayFollowUps = followUpRepository.countByNextFollowUpDate(LocalDate.now());
        long hotCustomers = customerLeadRepository.countByPriority("Hot");
        long closedDeals = customerLeadRepository.countByStatus("Closed Won");

        return new DashboardStatsDto(totalLeads, todayFollowUps, hotCustomers, closedDeals);
    }
}
