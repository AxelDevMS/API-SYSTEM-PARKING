package com.ams.dev.api.parking.permission.service.impl;

import com.ams.dev.api.parking.dto.ApiResponseDto;
import com.ams.dev.api.parking.permission.dto.PermissionDto;
import com.ams.dev.api.parking.permission.mapper.PermissionMapper;
import com.ams.dev.api.parking.permission.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public ApiResponseDto save(PermissionDto permissionDto, BindingResult bindingResult) {
        return null;
    }
}
