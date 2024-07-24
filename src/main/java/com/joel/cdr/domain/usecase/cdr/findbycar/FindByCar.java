package com.joel.cdr.domain.usecase.cdr.findbycar;

import com.joel.cdr.domain.usecase.cdr.model.ChargeDetailRecord;

import java.util.List;

public interface FindByCar {

    List<ChargeDetailRecord> query(String brand);
}
