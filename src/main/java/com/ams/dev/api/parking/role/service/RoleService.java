package com.ams.dev.api.parking.role.service;

import com.ams.dev.api.parking.dto.ApiResponseDto;
import com.ams.dev.api.parking.exception.BadRequestException;
import com.ams.dev.api.parking.exception.NotFoundException;
import com.ams.dev.api.parking.role.dto.RoleDto;
import org.springframework.validation.BindingResult;

public interface RoleService {

    ApiResponseDto executeCreateRole(RoleDto roleDto, BindingResult bindingResult) throws BadRequestException, NotFoundException;

}
