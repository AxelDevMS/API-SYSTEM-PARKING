package com.ams.dev.api.parking.permission.service;

import com.ams.dev.api.parking.dto.ApiResponseDto;
import com.ams.dev.api.parking.exception.BadRequestException;
import com.ams.dev.api.parking.exception.NotFoundException;
import com.ams.dev.api.parking.permission.dto.DisabledPermissionDto;
import com.ams.dev.api.parking.permission.dto.PermissionDto;
import com.ams.dev.api.parking.permission.persistence.entity.PermissionEntity;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

public interface PermissionService {
    ApiResponseDto executeCreatePermission(PermissionDto permissionDto, BindingResult bindingResult) throws BadRequestException;
    ApiResponseDto executeUpdatePermisison(UUID idPermission, PermissionDto permissionDto, BindingResult bindingResult) throws BadRequestException, NotFoundException;
    ApiResponseDto executeDisabledPermission(UUID idPermission, DisabledPermissionDto disabledDto) throws NotFoundException;
    ApiResponseDto executeListPermisisons(UUID idPermission, String name, String module, String status,  int page, int size) throws NotFoundException;
    ApiResponseDto executeGetPermission(UUID idPermission) throws NotFoundException;
    ApiResponseDto executeGetListPermisisons() throws NotFoundException;
    List<PermissionEntity> validatePermissions(List<PermissionDto> permissionDtos) throws BadRequestException,NotFoundException;
}
