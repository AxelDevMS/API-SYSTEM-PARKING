package com.ams.dev.api.parking.parkingLot.dto;

import com.ams.dev.api.parking.parkingLot.util.StatusParkingLot;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

public class ParkingLotDto {
    private UUID id;

    @NotNull(message = "El nombre del parking es obligatorio")
    @NotBlank(message = "El nombre del parking es obligatorio")
    private String name;

    @NotNull(message = "La ubicación del parking es obligatorio")
    @NotBlank(message = "La ubicación del parking es obligatorio")
    private String location;

    @NotNull(message = "La capacidad del parking es obligatorio")
    @NotBlank(message = "La capacidad del parking es obligatorio")
    private String capacity;

    @NotNull(message = "El status del parking es obligatorio")
    private StatusParkingLot status;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Mexico_City")
    private Date createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Mexico_City")
    private Date updatedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public StatusParkingLot getStatus() {
        return status;
    }

    public void setStatus(StatusParkingLot status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
