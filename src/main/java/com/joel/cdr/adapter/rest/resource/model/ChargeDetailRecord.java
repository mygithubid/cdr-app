package com.joel.cdr.adapter.rest.resource.model;

import java.time.LocalDateTime;

public record ChargeDetailRecord(
        Long id,
        String carId,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
