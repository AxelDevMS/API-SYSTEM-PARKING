package com.ams.dev.api.parking.permission.service;

import com.ams.dev.api.parking.dto.ApiResponseDto;
import com.ams.dev.api.parking.permission.dto.PermissionDto;
import org.springframework.validation.BindingResult;

public interface PermissionService {
    ApiResponseDto save(PermissionDto permissionDto, BindingResult bindingResult);

}
