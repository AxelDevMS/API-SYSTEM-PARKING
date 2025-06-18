package com.ams.dev.api.parking.user.dto;

import com.ams.dev.api.parking.parkingLot.dto.ParkingLotDto;
import com.ams.dev.api.parking.role.dto.RoleDto;
import com.ams.dev.api.parking.user.Utils.StatusUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class UserDto {

    private UUID id;

    @NotNull(message = "El nombre debe ser informado")
    @NotBlank(message = "El nombre no puede estar vacio")
    private String name;

    @NotNull(message = "El apellido debe ser informado")
    @NotBlank(message = "El apellido no puede estar vacio")
    private String lastname;

    @NotNull(message = "El numero telefonico debe ser informado")
    private BigDecimal phone;

    @NotNull(message = "La dirección debe ser informado")
    @NotBlank(message = "La dirección no puede estar vacio")
    private String location;

    @NotNull(message = "El username debe ser informado")
    @NotBlank(message = "El username no puede estar vacio")
    private String username;

    @NotNull(message = "El corre electronico debe ser informado")
    @NotBlank(message = "El correo electronico no puede estar vacio")
    private String email;

    @NotNull(message = "La confirmación de contraseña debe ser informado")
    @NotBlank(message = "La confirmación de contraseña no puede estar vacio")
    private String confirmPassword;

    @NotNull(message = "La contraseña debe ser informado")
    @NotBlank(message = "El contraseña no puede estar vacio")
    private String password;

    @NotNull(message = "El status debe ser informado")
    private StatusUser status;

    @NotNull(message = "El usuario debe tener un rol asignado")
    private RoleDto role;

    @NotNull(message = "El usuario debe tener un estacionamiento asignado")
    private ParkingLotDto parking;

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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public BigDecimal getPhone() {
        return phone;
    }

    public void setPhone(BigDecimal phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StatusUser getStatus() {
        return status;
    }

    public void setStatus(StatusUser status) {
        this.status = status;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    public ParkingLotDto getParking() {
        return parking;
    }

    public void setParking(ParkingLotDto parking) {
        this.parking = parking;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
