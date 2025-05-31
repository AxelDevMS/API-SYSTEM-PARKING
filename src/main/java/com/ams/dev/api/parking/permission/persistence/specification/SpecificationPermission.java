package com.ams.dev.api.parking.permission.persistence.specification;

import com.ams.dev.api.parking.permission.persistence.entity.PermissionEntity;
import com.ams.dev.api.parking.permission.util.ModuleSystem;
import com.ams.dev.api.parking.permission.util.NamePermission;
import com.ams.dev.api.parking.permission.util.StatusPermisison;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpecificationPermission {

    public static Specification<PermissionEntity> withFilter(UUID idPermision, String name, String module, String status){
        return (root,query,cb)->{
            List<Predicate> predicates = new ArrayList<>();

            if (idPermision != null && !idPermision.toString().isEmpty())
                predicates.add(cb.equal(root.get("id"), idPermision));

            if (module != null && !module.isEmpty())
                predicates.add(cb.equal(root.get("module"), ModuleSystem.valueOf(module)));

            if (name != null  && !name.isEmpty())
                predicates.add(cb.equal(root.get("name"), NamePermission.valueOf(name)));

            if (status != null  && !status.isEmpty())
                predicates.add(cb.equal(root.get("status"), StatusPermisison.valueOf(status)));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
