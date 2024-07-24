package com.joel.cdr.domain.usecase.cdr.create;

import com.joel.cdr.domain.usecase.cdr.gateway.ChargeDetailRecordGateway;
import com.joel.cdr.domain.usecase.cdr.model.ChargeDetailRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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
    void execute() {

        var start = LocalDateTime
                .parse("2019-12-31T19:15:30");
        var end = LocalDateTime
                .parse("2019-12-31T19:30:30");

        var domain = new com.joel.cdr.domain.model.ChargeDetailRecord(
                null,
                "carId",
                start,
                end
        );

        Mockito.when(chargeDetailRecordGateway.save(domain)).thenAnswer(invocationOnMock -> new com.joel.cdr.domain.model.ChargeDetailRecord(
                1L,
                "carId",
                start,
                end
        ));

        var record = new ChargeDetailRecord(
                1L,
                "carId",
                start,
                end
        );

        var savedRecord = createImpl.execute(record);

        assertEquals(record.id(), savedRecord.id());
        assertEquals(record.carId(), savedRecord.carId());
        assertEquals(record.startTime(), savedRecord.startTime());
        assertEquals(record.endTime(), savedRecord.endTime());
    }
}