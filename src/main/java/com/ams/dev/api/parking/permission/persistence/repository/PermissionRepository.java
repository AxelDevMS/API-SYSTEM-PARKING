package com.ams.dev.api.parking.permission.persistence.repository;

import com.ams.dev.api.parking.permission.persistence.entity.PermissionEntity;
import com.ams.dev.api.parking.permission.util.NamePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PermissionRepository extends JpaRepository<PermissionEntity, UUID> {

    Optional<PermissionEntity> findByName(NamePermission name);

}
