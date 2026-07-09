package com.satya.CRM.repository;

import com.satya.CRM.entity.CustomerLead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerLeadRepository extends JpaRepository<CustomerLead, Long> {

    long countByStatus(String status);
    long countByPriority(String priority);
}
