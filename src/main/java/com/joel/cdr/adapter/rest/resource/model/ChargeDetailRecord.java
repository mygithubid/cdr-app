package com.joel.cdr.adapter.rest.resource.model;

import org.springframework.util.Assert;

import java.time.LocalDateTime;

public record ChargeDetailRecord(
        Long id,
        String carId,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
    public ChargeDetailRecord {
        Assert.hasText(carId, "Invalid (null or empty) carId");
        Assert.isTrue(startTime != null, "Invalid (null) startTime");
        Assert.isTrue(endTime != null, "Invalid (null) endTime");
        Assert.isTrue(startTime.isBefore(endTime), "The startTime must be earlier than the endTime");
    }
}
