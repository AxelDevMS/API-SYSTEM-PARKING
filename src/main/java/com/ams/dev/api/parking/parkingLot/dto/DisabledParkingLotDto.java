package com.ams.dev.api.parking.parkingLot.dto;

import com.ams.dev.api.parking.parkingLot.util.StatusParkingLot;
import jakarta.validation.constraints.NotNull;

public class DisabledParkingLotDto {

    @NotNull(message = "El status del permiso es obligatorio")
    private StatusParkingLot status;

    public StatusParkingLot getStatus() {
        return status;
    }

    public void setStatus(StatusParkingLot status) {
        this.status = status;
    }
}
