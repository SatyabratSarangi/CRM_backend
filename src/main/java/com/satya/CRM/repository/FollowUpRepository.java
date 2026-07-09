package com.satya.CRM.repository;

import com.satya.CRM.entity.FollowUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FollowUpRepository extends JpaRepository<FollowUp, Long> {
    List<FollowUp> findByLeadIdOrderByFollowUpDateDesc(Long leadId);
    long countByNextFollowUpDate(LocalDate date);
    long countByNextFollowUpDateBeforeAndStatusNot(LocalDate date, String status);
}
