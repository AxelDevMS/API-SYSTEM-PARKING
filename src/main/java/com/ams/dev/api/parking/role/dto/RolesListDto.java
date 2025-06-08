package com.ams.dev.api.parking.role.dto;

import java.io.Serializable;
import java.util.List;

public class RolesListDto implements Serializable {

    private List<RoleDto> roles;
    private int totalElements;

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
}
