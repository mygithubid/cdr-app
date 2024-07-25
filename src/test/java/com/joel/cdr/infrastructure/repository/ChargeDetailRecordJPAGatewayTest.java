package com.joel.cdr.infrastructure.repository;

import com.joel.cdr.infrastructure.entity.ChargeDetailRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChargeDetailRecordJPAGatewayTest {

    ChargeDetailRecordJPARepository repository;

    ChargeDetailRecordJPAGateway gateway;

    @BeforeEach
    void setUp() {
        repository = mock(ChargeDetailRecordJPARepository.class);
        gateway = new ChargeDetailRecordJPAGateway(repository);
    }

    @Test
    void findById() {
        var start = LocalDateTime
                .parse("2019-12-31T19:15:30");
        var end = LocalDateTime
                .parse("2019-12-31T19:30:30");

        var entity = new ChargeDetailRecord(
                1L,
                "carId",
                start,
                end,
                1.0);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var record = gateway.findById(1L);

        var domain = new com.joel.cdr.domain.model.ChargeDetailRecord(
                1L,
                "carId",
                start,
                end,
                1.0
        );
        assertTrue(record.isPresent());
        assertEquals(domain.id(), record.get().id());
        assertEquals(domain.carId(), record.get().carId());
        assertEquals(domain.startTime(), record.get().startTime());
        assertEquals(domain.endTime(), record.get().endTime());
        assertEquals(domain.cost(), record.get().cost());
    }

    @Test
    void findAll() {
        var start = LocalDateTime
                .parse("2019-12-31T19:15:30");
        var end = LocalDateTime
                .parse("2019-12-31T19:30:30");

        var entity = new ChargeDetailRecord(
                1L,
                "carId",
                start,
                end,
                1.0);
        when(repository.findAll()).thenReturn(List.of(entity));

        var records = gateway.findAll();

        var domain = new com.joel.cdr.domain.model.ChargeDetailRecord(
                1L,
                "carId",
                start,
                end,
                1.0
        );
        assertFalse(records.isEmpty());
        assertEquals(domain.id(), records.get(0).id());
        assertEquals(domain.carId(), records.get(0).carId());
        assertEquals(domain.startTime(), records.get(0).startTime());
        assertEquals(domain.endTime(), records.get(0).endTime());
        assertEquals(domain.cost(), records.get(0).cost());
    }

    @Test
    void create() {
        var start = LocalDateTime
                .parse("2019-12-31T19:15:30");
        var end = LocalDateTime
                .parse("2019-12-31T19:30:30");

        var entity = new ChargeDetailRecord(
                1L,
                "carId",
                start,
                end,
                1.0);
        when(repository.save(any(ChargeDetailRecord.class))).thenReturn(entity);

        var record = gateway.save(new com.joel.cdr.domain.model.ChargeDetailRecord(
                null,
                "carId",
                start,
                end,
                1.0
        ));

        var domain = new com.joel.cdr.domain.model.ChargeDetailRecord(
                1L,
                "carId",
                start,
                end,
                1.0
        );

        assertEquals(domain.id(), record.id());
        assertEquals(domain.carId(), record.carId());
        assertEquals(domain.startTime(), record.startTime());
        assertEquals(domain.endTime(), record.endTime());
        assertEquals(domain.cost(), record.cost());
    }
}