package com.ams.dev.api.parking.permission.persistence.entity;

import com.ams.dev.api.parking.permission.util.ModuleSystem;
import com.ams.dev.api.parking.permission.util.NamePermission;
import com.ams.dev.api.parking.permission.util.StatusPermisison;
import com.ams.dev.api.parking.role.persistence.entity.RoleEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "permisison")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private NamePermission name;

    private String description;

    @Enumerated(EnumType.STRING)
    private StatusPermisison status;

    @Enumerated(EnumType.STRING)
    private ModuleSystem module;

    @ManyToMany(mappedBy = "permissions")
    private List<RoleEntity> roles;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
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

    public ModuleSystem getModule() {
        return module;
    }

    public void setModule(ModuleSystem module) {
        this.module = module;
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

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
}
