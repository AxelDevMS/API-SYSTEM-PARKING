package com.ams.dev.api.parking.permission.service.impl;

import com.ams.dev.api.parking.dto.ApiResponseDto;
import com.ams.dev.api.parking.dto.ValidateInputDto;
import com.ams.dev.api.parking.exception.BadRequestException;
import com.ams.dev.api.parking.permission.dto.PermissionDto;
import com.ams.dev.api.parking.permission.mapper.PermissionMapper;
import com.ams.dev.api.parking.permission.persistence.entity.PermissionEntity;
import com.ams.dev.api.parking.permission.persistence.repository.PermissionRepository;
import com.ams.dev.api.parking.permission.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private PermissionRepository permissionRepository;


    @Override
    public ApiResponseDto save(PermissionDto permissionDto, BindingResult bindingResult) throws BadRequestException {

        List<ValidateInputDto> listInputs = this.validateInputs(bindingResult);
        if (!listInputs.isEmpty())
            throw new BadRequestException("Campos invalidos",listInputs);


        PermissionEntity permissionBD = this.permissionRepository.findByName(permissionDto.getName()).orElse(null);
        if (permissionBD != null)
            throw new BadRequestException("Ya existe un permiso con el nombre " + permissionDto.getName());

        PermissionEntity save = this.permissionRepository.save(permissionMapper.convertToEntity(permissionDto));
        return new ApiResponseDto(HttpStatus.CREATED.value(),"El registro de inserto correctamente", permissionMapper.convertToDto(save));
    }


    private List<ValidateInputDto> validateInputs(BindingResult bindingResult){
        List<ValidateInputDto> validateFieldInputs = new ArrayList<>();
        if (bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(inputError ->{
                ValidateInputDto validateInputDto = new ValidateInputDto();
                validateInputDto.setInput(inputError.getField());
                validateInputDto.setInvalidMessage(inputError.getDefaultMessage());
                validateFieldInputs.add(validateInputDto);
            });
        }
        return validateFieldInputs;
    }


}
