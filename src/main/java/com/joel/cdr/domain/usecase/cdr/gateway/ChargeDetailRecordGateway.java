package com.joel.cdr.domain.usecase.cdr.gateway;

import com.joel.cdr.domain.model.ChargeDetailRecord;

import java.util.List;
import java.util.Optional;

public interface ChargeDetailRecordGateway {

    Optional<ChargeDetailRecord> findById(Long id);

    ChargeDetailRecord save(ChargeDetailRecord chargeDetailRecord);

    List<ChargeDetailRecord> findAll();
}
