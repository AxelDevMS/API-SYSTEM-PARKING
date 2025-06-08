package com.ams.dev.api.parking.role.dto;

import com.ams.dev.api.parking.permission.dto.PermissionDto;
import com.ams.dev.api.parking.role.util.NameRole;
import com.ams.dev.api.parking.role.util.StatusRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.util.Date;
import java.util.List;
import java.util.UUID;

public class RoleDto {

    private UUID id;

    @NotNull(message = "El módulo del permiso es obligatorio")
    private NameRole name;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(min = 10, message = "La descripción debe ser mínimo de 10 caracteres")
    private String description;

    @NotNull(message = "El status del permiso es obligatorio")
    private StatusRole status;

    @NotNull(message = "Debe tener por lo menos un permisos asociado")
    private List<PermissionDto> permissions;

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

    public NameRole getName() {
        return name;
    }

    public void setName(NameRole name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusRole getStatus() {
        return status;
    }

    public void setStatus(StatusRole status) {
        this.status = status;
    }

    public List<PermissionDto> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDto> permissions) {
        this.permissions = permissions;
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
