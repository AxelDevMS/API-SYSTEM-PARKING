package com.ams.dev.api.parking.permission.persistence.repository;

import com.ams.dev.api.parking.permission.persistence.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<PermissionEntity, UUID> {

}
