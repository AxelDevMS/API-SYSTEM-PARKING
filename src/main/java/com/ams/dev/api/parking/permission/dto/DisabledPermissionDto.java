package com.ams.dev.api.parking.permission.dto;

import com.ams.dev.api.parking.permission.util.StatusPermisison;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class DisabledPermissionDto implements Serializable {

    @NotNull(message = "El status del permiso es obligatorio")
    private StatusPermisison status;

    public StatusPermisison getStatus() {
        return status;
    }

    public void setStatus(StatusPermisison status) {
        this.status = status;
    }
}
