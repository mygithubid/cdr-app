package com.joel.cdr.domain.usecase.cdr.findbyid;

import com.joel.cdr.domain.model.ChargeDetailRecord;
import com.joel.cdr.domain.usecase.cdr.gateway.ChargeDetailRecordGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByIdImplTest {

    ChargeDetailRecordGateway chargeDetailRecordGateway;
    FindById findById;

    @BeforeEach
    void setUp() {
        chargeDetailRecordGateway = mock(ChargeDetailRecordGateway.class);
        findById = new FindByIdImpl(chargeDetailRecordGateway);
    }

    @Test
    void findNoRecord() {
        when(chargeDetailRecordGateway.findById(1L)).thenReturn(Optional.empty());
        var record = findById.query(1L);
        assertTrue(record.isEmpty());
    }

    @Test
    void findRecord() {
        var start = LocalDateTime
                .parse("2019-12-31T19:15:30");
        var end = LocalDateTime
                .parse("2019-12-31T19:30:30");

        var domain = new ChargeDetailRecord(
                1L,
                "carId",
                start,
                end
        );
        when(chargeDetailRecordGateway.findById(1L)).thenReturn(Optional.of(domain));

        var record = findById.query(1L);
        assertTrue(record.isPresent());
        assertEquals(domain.id(), record.get().id());
        assertEquals(domain.carId(), record.get().carId());
        assertEquals(domain.startTime(), record.get().startTime());
        assertEquals(domain.endTime(), record.get().endTime());
    }
}