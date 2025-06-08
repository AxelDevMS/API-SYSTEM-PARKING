package com.ams.dev.api.parking.parkingLot.persistence.repository;

import com.ams.dev.api.parking.parkingLot.persistence.entity.ParkingLotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParkingLotRepository extends JpaRepository<ParkingLotEntity, UUID> {
}
