package com.ams.dev.api.parking.permission.persistence.repository;

import com.ams.dev.api.parking.permission.persistence.entity.PermissionEntity;
import com.ams.dev.api.parking.permission.util.NamePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface PermissionRepository extends JpaRepository<PermissionEntity, UUID> , JpaSpecificationExecutor<PermissionEntity> {

    Optional<PermissionEntity> findByName(NamePermission name);

}
