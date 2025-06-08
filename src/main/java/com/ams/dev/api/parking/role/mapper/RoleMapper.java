package com.ams.dev.api.parking.role.mapper;

import com.ams.dev.api.parking.role.dto.RoleDto;
import com.ams.dev.api.parking.role.persistence.entity.RoleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    @Autowired
    private ModelMapper modelMapper;

    public RoleDto convertToDto(RoleEntity roleEntity){
        return this.modelMapper.map(roleEntity, RoleDto.class);
    }

    public RoleEntity convertToEntity(RoleDto roleDto){
        return this.modelMapper.map(roleDto, RoleEntity.class);
    }
}
