package com.ams.dev.api.parking.role.persistence.filter;

import com.ams.dev.api.parking.role.persistence.entity.RoleEntity;
import com.ams.dev.api.parking.role.util.StatusRole;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FilterRole {
    public static Specification<RoleEntity> withFilter(UUID idRole, String name, String status){
        return(root, query, cb)->{
            List<Predicate> predicates = new ArrayList<>();

            if (idRole != null && !idRole.toString().isEmpty())
                predicates.add(cb.equal(root.get("id"), idRole));

            if (name != null && !name.isEmpty())
                predicates.add(cb.equal(root.get("name"), name));

            if (status != null && !status.isEmpty())
                predicates.add(cb.equal(root.get("status"), StatusRole.valueOf(status)));

            return cb.and(predicates.toArray(new Predicate[0]));

        };
    }
}
