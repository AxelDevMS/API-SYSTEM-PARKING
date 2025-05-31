package com.ams.dev.api.parking.permission.mapper;

import com.ams.dev.api.parking.permission.dto.PermissionDto;
import com.ams.dev.api.parking.permission.persistence.entity.PermissionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PermissionDto convertToDto(PermissionEntity permission){
        return this.modelMapper.map(permission, PermissionDto.class);
    }

    public PermissionEntity convertToEntity(PermissionDto permissionDto){
        return this.modelMapper.map(permissionDto, PermissionEntity.class);
    }
}
