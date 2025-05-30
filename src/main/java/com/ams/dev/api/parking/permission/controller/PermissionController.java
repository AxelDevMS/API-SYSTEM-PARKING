package com.ams.dev.api.parking.permission.controller;

import com.ams.dev.api.parking.dto.ApiResponseDto;
import com.ams.dev.api.parking.exception.BadRequestException;
import com.ams.dev.api.parking.permission.dto.PermissionDto;
import com.ams.dev.api.parking.permission.service.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/parking/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    @PostMapping("/save")
    public ResponseEntity<?> executeCrate(@Valid  @RequestBody PermissionDto permissionDto, BindingResult bindingResult) throws BadRequestException {
        ApiResponseDto response = this.permissionService.save(permissionDto,bindingResult);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
