package com.satya.CRM.repository;

import com.satya.CRM.entity.LeadType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadTypeRepository extends JpaRepository<LeadType, Long> {
}
