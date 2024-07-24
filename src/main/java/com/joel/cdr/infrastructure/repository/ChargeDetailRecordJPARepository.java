package com.joel.cdr.infrastructure.repository;

import com.joel.cdr.infrastructure.entity.ChargeDetailRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeDetailRecordJPARepository extends JpaRepository<ChargeDetailRecord, Long> {
}
