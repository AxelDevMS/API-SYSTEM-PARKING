package com.ams.dev.api.parking.parkingLot.persistence.filter;

import com.ams.dev.api.parking.parkingLot.persistence.entity.ParkingLotEntity;
import com.ams.dev.api.parking.parkingLot.util.StatusParkingLot;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParkingLotFilter {
    public static Specification<ParkingLotEntity> wwithFilter(UUID parkingId, String status){
        return (root, query, cb)->{
            List<Predicate> predicates = new ArrayList<>();

            if (parkingId != null && !parkingId.toString().isEmpty())
                predicates.add(cb.equal(root.get("id"), parkingId));

            if (status != null && !status.isEmpty())
                predicates.add(cb.equal(root.get("status"), StatusParkingLot.valueOf(status)));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
