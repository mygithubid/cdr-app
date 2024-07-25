package com.joel.cdr.domain.usecase.cdr.findbycar;

import com.joel.cdr.domain.model.ChargeDetailRecord;
import com.joel.cdr.domain.usecase.cdr.gateway.ChargeDetailRecordGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByCarImplTest {

    ChargeDetailRecordGateway chargeDetailRecordGateway;
    FindByCarImpl findByCar;

    @BeforeEach
    void setUp() {
        chargeDetailRecordGateway = mock(ChargeDetailRecordGateway.class);
        findByCar = new FindByCarImpl(chargeDetailRecordGateway);
    }

    @Test
    void findNoRecords() {
        when(chargeDetailRecordGateway.findAll()).thenReturn(List.of());
        var records = findByCar.query("XYZ");
        assertTrue(records.isEmpty());
    }

    @Test
    void findRecords() {
        var start = LocalDateTime
                .parse("2019-12-31T19:15:30");
        var end = LocalDateTime
                .parse("2019-12-31T19:30:30");

        var record = new ChargeDetailRecord(
                1L,
                "carId",
                start,
                end,
                1.0
        );
        when(chargeDetailRecordGateway.findAll()).thenReturn(List.of(record));

        var records = findByCar.query("carId");
        assertFalse(records.isEmpty());
        assertEquals(record.id(), records.get(0).id());
        assertEquals(record.carId(), records.get(0).carId());
        assertEquals(record.startTime(), records.get(0).startTime());
        assertEquals(record.endTime(), records.get(0).endTime());
    }
}