package com.ams.dev.api.parking.permission.service;

import com.ams.dev.api.parking.dto.ApiResponseDto;
import com.ams.dev.api.parking.exception.BadRequestException;
import com.ams.dev.api.parking.exception.NotFoundException;
import com.ams.dev.api.parking.permission.dto.PermissionDto;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface PermissionService {
    ApiResponseDto executeCreatePermission(PermissionDto permissionDto, BindingResult bindingResult) throws BadRequestException;
    ApiResponseDto executeUpdatePermisison(UUID idPermission, PermissionDto permissionDto, BindingResult bindingResult) throws BadRequestException, NotFoundException;

}
