package com.joel.cdr.infrastructure.repository;

import com.joel.cdr.domain.model.ChargeDetailRecord;
import com.joel.cdr.domain.usecase.cdr.gateway.ChargeDetailRecordGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ChargeDetailRecordJPAGateway implements ChargeDetailRecordGateway {

    private final ChargeDetailRecordJPARepository chargeDetailRecordJPARepository;

    public ChargeDetailRecordJPAGateway(ChargeDetailRecordJPARepository chargeDetailRecordJPARepository) {
        this.chargeDetailRecordJPARepository = chargeDetailRecordJPARepository;
    }

    @Override
    public Optional<ChargeDetailRecord> findById(Long id) {
        var record = chargeDetailRecordJPARepository.findById(id);
        return record.map(this::toRecord);
    }

    @Override
    public ChargeDetailRecord save(ChargeDetailRecord chargeDetailRecord) {
        var entity = new com.joel.cdr.infrastructure.entity.ChargeDetailRecord(
                chargeDetailRecord.id(),
                chargeDetailRecord.carId(),
                chargeDetailRecord.startTime(),
                chargeDetailRecord.endTime());
        var persisted = chargeDetailRecordJPARepository.save(entity);
        return toRecord(persisted);
    }

    @Override
    public List<ChargeDetailRecord> findAll() {
        return chargeDetailRecordJPARepository.findAll().stream().map(this::toRecord).toList();
    }

    private ChargeDetailRecord toRecord(final com.joel.cdr.infrastructure.entity.ChargeDetailRecord entity) {
        return new ChargeDetailRecord(
                entity.getId(),
                entity.getCarId(),
                entity.getStartTime(),
                entity.getEndTime()
        );
    }
}
