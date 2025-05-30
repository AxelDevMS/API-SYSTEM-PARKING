package com.ams.dev.api.parking.permission.persistence.entity;

import com.ams.dev.api.parking.permission.util.ModuleSystem;
import com.ams.dev.api.parking.permission.util.NamePermission;
import com.ams.dev.api.parking.permission.util.StatusPermisison;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permisison")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NamePermission name;

    private String description;

    @Enumerated(EnumType.STRING)
    private StatusPermisison status;

    @Enumerated(EnumType.STRING)
    private ModuleSystem module;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

}
