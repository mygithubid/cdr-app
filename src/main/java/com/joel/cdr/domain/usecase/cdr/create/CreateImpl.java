package com.joel.cdr.domain.usecase.cdr.create;

import com.joel.cdr.domain.usecase.cdr.gateway.ChargeDetailRecordGateway;
import com.joel.cdr.domain.usecase.cdr.model.ChargeDetailRecord;
import org.springframework.stereotype.Component;

@Component("com.joel.cdr.domain.usecase.cdr.create.CreateImpl")
public class CreateImpl implements Create {

    private final ChargeDetailRecordGateway chargeDetailRecordGateway;

    public CreateImpl(ChargeDetailRecordGateway chargeDetailRecordGateway) {
        this.chargeDetailRecordGateway = chargeDetailRecordGateway;
    }

    @Override
    public ChargeDetailRecord execute(ChargeDetailRecord chargeDetailRecord) {
        var domainDeviceRequest = new com.joel.cdr.domain.model.ChargeDetailRecord(
                null,
                chargeDetailRecord.carId(),
                chargeDetailRecord.startTime(),
                chargeDetailRecord.endTime()
        );

        var savedDomain = chargeDetailRecordGateway.save(domainDeviceRequest);
        return new ChargeDetailRecord(
                savedDomain.id(),
                savedDomain.carId(),
                savedDomain.startTime(),
                savedDomain.endTime()
        );
    }
}
