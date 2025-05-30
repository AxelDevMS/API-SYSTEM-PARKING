package com.ams.dev.api.parking.permission.dto;

import com.ams.dev.api.parking.permission.util.ModuleSystem;
import com.ams.dev.api.parking.permission.util.NamePermission;
import com.ams.dev.api.parking.permission.util.StatusPermisison;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;


import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto {
    private UUID id;

    @NotBlank(message = "El nombre es obligatorio")
    @NotNull(message = "El nombre es obligatorio")
    private NamePermission name;

    @NotBlank(message = "El modulo del permiso es obligatorio")
    @NotNull(message = "El modulo del permiso es obligatorio")
    private ModuleSystem module;

    @NotBlank(message = "la descripción es obligatoria")
    @NotNull(message = "la descripción es obligatoria")
    @Min(value = 10, message = "la descrición debe ser minimo de 10 caracteres")
    private String description;

    private StatusPermisison status;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Mexico_City")
    private Date createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Mexico_City")
    private Date updatedAt;
}
