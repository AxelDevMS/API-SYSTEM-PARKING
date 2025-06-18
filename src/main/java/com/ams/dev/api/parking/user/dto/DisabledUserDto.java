package com.ams.dev.api.parking.user.dto;

import com.ams.dev.api.parking.user.Utils.StatusUser;
import jakarta.validation.constraints.NotNull;

public class DisabledUserDto {

    @NotNull(message = "El status debe ser informado")
    private StatusUser status;

    public StatusUser getStatus() {
        return status;
    }

    public void setStatus(StatusUser status) {
        this.status = status;
    }
}
