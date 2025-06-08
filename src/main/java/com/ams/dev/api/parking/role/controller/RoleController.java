package com.ams.dev.api.parking.role.controller;


import com.ams.dev.api.parking.dto.ApiResponseDto;
import com.ams.dev.api.parking.exception.BadRequestException;
import com.ams.dev.api.parking.exception.NotFoundException;
import com.ams.dev.api.parking.role.dto.DisabledRoleDto;
import com.ams.dev.api.parking.role.dto.RoleDto;
import com.ams.dev.api.parking.role.service.RoleService;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/parking/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponseDto> executeCreateRole(@Valid @RequestBody RoleDto roleDto, BindingResult bindingResult) throws BadRequestException, NotFoundException {
        ApiResponseDto response = this.roleService.executeCreateRole(roleDto, bindingResult);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDto> executeUpdateRole(@PathVariable UUID id, @Valid @RequestBody RoleDto roleDto, BindingResult bindingResult) throws BadRequestException, NotFoundException {
        ApiResponseDto response = this.roleService.executeUpdateRole(id, roleDto, bindingResult);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/get/alls")
    public ResponseEntity<ApiResponseDto> executeGetListRoles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) UUID idRole,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String status
    ) throws NotFoundException {
        ApiResponseDto response = this.roleService.executeGetListRoles(page, size, idRole, name, status);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("disabled/{id}")
    public ResponseEntity<ApiResponseDto> executeDisabledRole(@PathVariable UUID id, @RequestBody DisabledRoleDto disabledRoleDto) throws NotFoundException {
        ApiResponseDto response = this.roleService.executeDisabledRole(id, disabledRoleDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/select/alls")
    public ResponseEntity<ApiResponseDto> executeGetRolesBySelect() throws NotFoundException {
        ApiResponseDto response = this.roleService.executeGetListBySelecy();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponseDto> executeGetRoleBYiD(@PathVariable UUID id) throws NotFoundException {
        ApiResponseDto response = this.roleService.executeGteyRoleById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
