package com.ams.dev.api.parking.permission.dto;

import com.ams.dev.api.parking.permission.util.ModuleSystem;
import com.ams.dev.api.parking.permission.util.NamePermission;
import com.ams.dev.api.parking.permission.util.StatusPermisison;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.util.Date;
import java.util.UUID;



public class PermissionDto {
    private UUID id;

    @NotNull(message = "El nombre es obligatorio")
    private NamePermission name;

    @NotNull(message = "El módulo del permiso es obligatorio")
    private ModuleSystem module;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 10, message = "La descripción debe ser mínimo de 10 caracteres")
    private String description;

    private StatusPermisison status;

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

    public NamePermission getName() {
        return name;
    }

    public void setName(NamePermission name) {
        this.name = name;
    }

    public ModuleSystem getModule() {
        return module;
    }

    public void setModule(ModuleSystem module) {
        this.module = module;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusPermisison getStatus() {
        return status;
    }

    public void setStatus(StatusPermisison status) {
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
