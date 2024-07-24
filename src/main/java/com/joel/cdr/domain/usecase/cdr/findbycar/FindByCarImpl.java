package com.joel.cdr.domain.usecase.cdr.findbycar;

import com.joel.cdr.domain.usecase.cdr.gateway.ChargeDetailRecordGateway;
import com.joel.cdr.domain.usecase.cdr.model.ChargeDetailRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("com.joel.cdr.domain.usecase.cdr.findbycar.FindByCarImpl")
public class FindByCarImpl implements FindByCar {

    private final ChargeDetailRecordGateway chargeDetailRecordGateway;

    public FindByCarImpl(ChargeDetailRecordGateway chargeDetailRecordGateway) {
        this.chargeDetailRecordGateway = chargeDetailRecordGateway;
    }


    @Override
    public List<ChargeDetailRecord> query(String carId) {
        var records = chargeDetailRecordGateway.findAll();
        return records.stream()
                .filter(device -> device.carId().equals(carId))
                    .map(this::toRecord)
                    .toList();
    }

    private ChargeDetailRecord toRecord(final com.joel.cdr.domain.model.ChargeDetailRecord domainChargeDetailRecord) {
        return new ChargeDetailRecord(
                domainChargeDetailRecord.id(),
                domainChargeDetailRecord.carId(),
                domainChargeDetailRecord.startTime(),
                domainChargeDetailRecord.endTime()
        );
    }
}
