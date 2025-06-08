package com.ams.dev.api.parking.role.service.impl;

import com.ams.dev.api.parking.dto.ApiResponseDto;
import com.ams.dev.api.parking.dto.ValidateInputDto;
import com.ams.dev.api.parking.exception.BadRequestException;
import com.ams.dev.api.parking.exception.NotFoundException;
import com.ams.dev.api.parking.permission.persistence.entity.PermissionEntity;
import com.ams.dev.api.parking.permission.service.PermissionService;
import com.ams.dev.api.parking.role.dto.RoleDto;
import com.ams.dev.api.parking.role.mapper.RoleMapper;
import com.ams.dev.api.parking.role.persistence.entity.RoleEntity;
import com.ams.dev.api.parking.role.persistence.repository.RoleRepository;
import com.ams.dev.api.parking.role.service.RoleService;
import com.ams.dev.api.parking.role.util.StatusRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionService permissionService;

    @Override
    public ApiResponseDto executeUpdateRole(UUID idRole, RoleDto roleDto, BindingResult bindingResult) throws BadRequestException, NotFoundException {
        List<ValidateInputDto> inputs = this.validateInputs(bindingResult);
        if (!inputs.isEmpty())
            throw  new BadRequestException("Campos invalidos!", inputs);

        RoleEntity roleBD = this.roleRepository.findById(idRole).orElse(null);
        if (roleBD == null)
            throw new NotFoundException("No existe este rol con id "+ idRole);

        List<PermissionEntity> permissionsBD = this.permissionService.validatePermissions(roleDto.getPermissions());

        roleBD.setName(roleDto.getName());
        roleBD.setDescription(roleDto.getDescription());
        roleBD.setStatus(roleDto.getStatus());
        roleBD.setPermissions(permissionsBD);

        RoleEntity roleSave = this.roleRepository.save(roleBD);
        return new ApiResponseDto(HttpStatus.OK.value(),"El registro se actualizo exitosamente",this.roleMapper.convertToDto(roleSave));
    }

    @Override
    public ApiResponseDto executeCreateRole(RoleDto roleDto, BindingResult bindingResult) throws BadRequestException, NotFoundException {
        List<ValidateInputDto> inputs = this.validateInputs(bindingResult);
        if (!inputs.isEmpty())
            throw  new BadRequestException("Campos invalidos!", inputs);

        RoleEntity roleBD = this.roleRepository.findByName(roleDto.getName()).orElse(null);
        if (roleBD != null)
            throw new NotFoundException("Ya existe un rol con este nombre "+ roleDto.getName());

        List<PermissionEntity> permissionsBD = this.permissionService.validatePermissions(roleDto.getPermissions());

        RoleEntity saveRole = this.roleMapper.convertToEntity(roleDto);
        saveRole.setPermissions(permissionsBD);
        saveRole = this.roleRepository.save(saveRole);

        return new ApiResponseDto(HttpStatus.CREATED.value(),"El registro s eha insertado correctamente", this.roleMapper.convertToDto(saveRole));
    }



    private List<ValidateInputDto> validateInputs(BindingResult bindingResult){
        List<ValidateInputDto> inputs = new ArrayList<>();
        if (bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(inputError ->{
                ValidateInputDto input =  new ValidateInputDto();
                input.setInput(inputError.getField());
                input.setInvalidMessage(inputError.getDefaultMessage());
                inputs.add(input);
            });
        }
        return inputs;
    }
}
