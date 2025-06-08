package com.ams.dev.api.parking.role.persistence.repository;

import com.ams.dev.api.parking.role.persistence.entity.RoleEntity;
import com.ams.dev.api.parking.role.util.NameRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID>, JpaSpecificationExecutor<RoleEntity> {

    Optional<RoleEntity> findByName(NameRole name);
}
