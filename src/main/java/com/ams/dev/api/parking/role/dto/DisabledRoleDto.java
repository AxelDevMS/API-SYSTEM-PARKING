package com.ams.dev.api.parking.role.dto;

import com.ams.dev.api.parking.role.util.StatusRole;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class DisabledRoleDto implements Serializable {

    @NotNull(message = "El status del permiso es obligatorio")
    private StatusRole status;

    public StatusRole getStatus() {
        return status;
    }

    public void setStatus(StatusRole status) {
        this.status = status;
    }
}
