package com.joel.cdr.infrastructure.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "charge_detail_records")
public class ChargeDetailRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_id")
    private String carId;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "cost")
    private Double cost;

    public ChargeDetailRecord() {}

    public ChargeDetailRecord(
            Long id,
            String carId,
            LocalDateTime startTime,
            LocalDateTime endTime,
            Double cost) {
        this.id = id;
        this.carId = carId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public String getCarId() {
        return carId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Double getCost() { return cost; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCarId(String brand) {
        this.carId = brand;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setCost(Double cost) { this.cost = cost; }
}