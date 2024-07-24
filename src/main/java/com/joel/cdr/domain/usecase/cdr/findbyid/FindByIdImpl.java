package com.joel.cdr.domain.usecase.cdr.findbyid;

import com.joel.cdr.domain.usecase.cdr.gateway.ChargeDetailRecordGateway;
import com.joel.cdr.domain.usecase.cdr.model.ChargeDetailRecord;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("com.joel.cdr.domain.usecase.cdr.findbyid.FindByIdImpl")
public class FindByIdImpl implements FindById {

    private final ChargeDetailRecordGateway chargeDetailRecordGateway;

    public FindByIdImpl(ChargeDetailRecordGateway chargeDetailRecordGateway) {
        this.chargeDetailRecordGateway = chargeDetailRecordGateway;
    }

    @Override
    public Optional<ChargeDetailRecord> query(Long id) {
        var domain = chargeDetailRecordGateway.findById(id);
        if (domain.isEmpty()) {
            return Optional.empty();
        }

        var record = domain.get();
        return Optional.of(new ChargeDetailRecord(
                record.id(),
                record.carId(),
                record.startTime(),
                record.endTime()
        ));
    }
}
