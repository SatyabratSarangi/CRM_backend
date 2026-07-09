package com.satya.CRM.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatsDto {
    private long totalLeads;
    private long todayFollowUps;
    private long hotCustomers;
    private long closedDeals;
}
