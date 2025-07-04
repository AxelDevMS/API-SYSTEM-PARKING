package com.ams.dev.api.parking.permission.controller;

import com.ams.dev.api.parking.dto.ApiResponseDto;
import com.ams.dev.api.parking.exception.BadRequestException;
import com.ams.dev.api.parking.exception.NotFoundException;
import com.ams.dev.api.parking.permission.dto.DisabledPermissionDto;
import com.ams.dev.api.parking.permission.dto.PermissionDto;
import com.ams.dev.api.parking.permission.service.PermissionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/parking/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    @PostMapping("/save")
    public ResponseEntity<?> executeCreatePermission(@Valid  @RequestBody PermissionDto permissionDto, BindingResult bindingResult) throws BadRequestException {
        ApiResponseDto response = this.permissionService.executeCreatePermission(permissionDto,bindingResult);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDto> executeUpdatePermission(@PathVariable UUID id, @Valid @RequestBody PermissionDto permissionDto, BindingResult bindingResult) throws BadRequestException, NotFoundException {
        ApiResponseDto response = this.permissionService.executeUpdatePermisison(id, permissionDto, bindingResult);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PatchMapping("/disabled/{id}")
    public ResponseEntity<ApiResponseDto> executeDisabledPermission(@PathVariable UUID id, @Valid @RequestBody DisabledPermissionDto permissionDto) throws NotFoundException {
        ApiResponseDto response = this.permissionService.executeDisabledPermission(id,permissionDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/alls")
    public ResponseEntity<ApiResponseDto> executeListPermissions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) UUID idPermission,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String module,
            @RequestParam(required = false) String status
    ) throws NotFoundException{
        ApiResponseDto response = this.permissionService.executeListPermisisons(idPermission, name, module, status, page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{idPermission}")
    public ResponseEntity<ApiResponseDto> executeGetPermission(@PathVariable UUID idPermission) throws NotFoundException {
        ApiResponseDto response = this.permissionService.executeGetPermission(idPermission);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/alls/select")
    public ResponseEntity<ApiResponseDto> executeGetPermissionBySelect() throws NotFoundException {
        ApiResponseDto response = this.permissionService.executeGetListPermisisons();
        return ResponseEntity.ok(response);
    }


}
