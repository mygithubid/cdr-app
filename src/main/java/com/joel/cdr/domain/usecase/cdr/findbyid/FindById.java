package com.joel.cdr.domain.usecase.cdr.findbyid;

import com.joel.cdr.domain.usecase.cdr.model.ChargeDetailRecord;

import java.util.Optional;

public interface FindById {

    Optional<ChargeDetailRecord> query(Long id);
}
