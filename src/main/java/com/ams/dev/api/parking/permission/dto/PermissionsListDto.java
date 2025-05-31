package com.ams.dev.api.parking.permission.dto;

import java.io.Serializable;
import java.util.List;

public class PermissionsListDto implements Serializable {
    private List<PermissionDto> permissionDtos;
    private int totalElements;

    public List<PermissionDto> getPermissionDtos() {
        return permissionDtos;
    }

    public void setPermissionDtos(List<PermissionDto> permissionDtos) {
        this.permissionDtos = permissionDtos;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
}
