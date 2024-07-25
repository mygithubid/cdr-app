package com.joel.cdr.domain.usecase.cdr.create;

import com.joel.cdr.domain.usecase.cdr.gateway.ChargeDetailRecordGateway;
import com.joel.cdr.domain.usecase.cdr.model.ChargeDetailRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateImplTest {

    ChargeDetailRecordGateway chargeDetailRecordGateway;
    CreateImpl createImpl;

    @BeforeEach
    void setUp() {
        chargeDetailRecordGateway = mock(ChargeDetailRecordGateway.class);
        createImpl = new CreateImpl(chargeDetailRecordGateway);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSuccessWithNonOverlappingDates() {
        var start = LocalDateTime
                .parse("2019-12-31T19:15:30");
        var end = start.plusMinutes(15);

        var domain = new com.joel.cdr.domain.model.ChargeDetailRecord(
                null,
                "carId",
                start,
                end,
                1.0
        );

        when(chargeDetailRecordGateway.findAll()).thenAnswer(invocationOnMock -> Arrays.asList(new com.joel.cdr.domain.model.ChargeDetailRecord(
                1L,
                "carId",
                start.minusDays(2),
                start.minusDays(1),
                1.0
        )));
        when(chargeDetailRecordGateway.save(domain)).thenAnswer(invocationOnMock -> new com.joel.cdr.domain.model.ChargeDetailRecord(
                1L,
                "carId",
                start,
                end,
                1.0
        ));

        var record = new ChargeDetailRecord(
                1L,
                "carId",
                start,
                end,
                1.0
        );

        var savedRecord = createImpl.execute(record);

        assertEquals(record.id(), savedRecord.id());
        assertEquals(record.carId(), savedRecord.carId());
        assertEquals(record.startTime(), savedRecord.startTime());
        assertEquals(record.endTime(), savedRecord.endTime());
    }

    @Test
    void testFailWithOverlappingDates() {
        var start = LocalDateTime
                .parse("2019-12-31T19:15:30");
        var end = start.plusMinutes(15);

        var domain = new com.joel.cdr.domain.model.ChargeDetailRecord(
                null,
                "carId",
                start,
                end,
                1.0
        );

        when(chargeDetailRecordGateway.findAll()).thenAnswer(invocationOnMock -> Arrays.asList(new com.joel.cdr.domain.model.ChargeDetailRecord(
                1L,
                "carId",
                start,
                start,
                1.0
        )));
        when(chargeDetailRecordGateway.save(domain)).thenAnswer(invocationOnMock -> new com.joel.cdr.domain.model.ChargeDetailRecord(
                1L,
                "carId",
                start,
                end,
                1.0
        ));

        var record = new ChargeDetailRecord(
                1L,
                "carId",
                start,
                end,
                1.0
        );

        assertThatThrownBy(() -> createImpl.execute(record))
                .isInstanceOf(IllegalArgumentException.class);
    }
}