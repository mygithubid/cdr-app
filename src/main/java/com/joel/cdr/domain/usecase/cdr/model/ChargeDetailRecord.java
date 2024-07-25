package com.joel.cdr.domain.usecase.cdr.model;

import java.time.LocalDateTime;

public record ChargeDetailRecord(
        Long id,
        String carId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        Double cost
) {
}
