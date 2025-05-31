package com.ams.dev.api.parking.role.persistence.repository;

import com.ams.dev.api.parking.role.persistence.entity.RoleEntity;
import com.ams.dev.api.parking.role.util.NameRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

    Optional<RoleEntity> findByName(NameRole name);
}
