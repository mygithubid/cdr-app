package com.joel.cdr.domain.usecase.cdr.create;

import com.joel.cdr.domain.usecase.cdr.gateway.ChargeDetailRecordGateway;
import com.joel.cdr.domain.usecase.cdr.model.ChargeDetailRecord;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component("com.joel.cdr.domain.usecase.cdr.create.CreateImpl")
public class CreateImpl implements Create {

    private final ChargeDetailRecordGateway chargeDetailRecordGateway;

    public CreateImpl(ChargeDetailRecordGateway chargeDetailRecordGateway) {
        this.chargeDetailRecordGateway = chargeDetailRecordGateway;
    }

    @Override
    public ChargeDetailRecord execute(ChargeDetailRecord chargeDetailRecord) {
        synchronized (chargeDetailRecordGateway) {
            final Optional<LocalDateTime> maxEndTime
                    = chargeDetailRecordGateway.findAll().stream()
                            .map(com.joel.cdr.domain.model.ChargeDetailRecord::endTime)
                            .max(LocalDateTime::compareTo);

            if (maxEndTime.isPresent() && (
                    chargeDetailRecord.startTime().isBefore(maxEndTime.get())
                    || chargeDetailRecord.startTime().equals(maxEndTime.get()))) {
                throw new IllegalArgumentException(
                        "The startTime " + chargeDetailRecord.startTime()
                                + " must be more recent than the most recent end time " + maxEndTime.get());
            }

            var domainDeviceRequest = new com.joel.cdr.domain.model.ChargeDetailRecord(
                    null,
                    chargeDetailRecord.carId(),
                    chargeDetailRecord.startTime(),
                    chargeDetailRecord.endTime(),
                    chargeDetailRecord.cost()
            );

            var savedDomain = chargeDetailRecordGateway.save(domainDeviceRequest);
            return new ChargeDetailRecord(
                    savedDomain.id(),
                    savedDomain.carId(),
                    savedDomain.startTime(),
                    savedDomain.endTime(),
                    savedDomain.cost()
            );
        }
    }
}