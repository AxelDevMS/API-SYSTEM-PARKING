package com.ams.dev.api.parking.permission.service.impl;

import com.ams.dev.api.parking.dto.ApiResponseDto;
import com.ams.dev.api.parking.dto.ValidateInputDto;
import com.ams.dev.api.parking.exception.BadRequestException;
import com.ams.dev.api.parking.exception.NotFoundException;
import com.ams.dev.api.parking.permission.dto.DisabledPermissionDto;
import com.ams.dev.api.parking.permission.dto.PermissionDto;
import com.ams.dev.api.parking.permission.dto.PermissionsListDto;
import com.ams.dev.api.parking.permission.mapper.PermissionMapper;
import com.ams.dev.api.parking.permission.persistence.entity.PermissionEntity;
import com.ams.dev.api.parking.permission.persistence.repository.PermissionRepository;
import com.ams.dev.api.parking.permission.persistence.specification.SpecificationPermission;
import com.ams.dev.api.parking.permission.service.PermissionService;
import com.ams.dev.api.parking.permission.util.StatusPermisison;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public ApiResponseDto executeGetPermission(UUID idPermission) throws NotFoundException {
        PermissionEntity permissionBD = this.permissionRepository.findById(idPermission).orElse(null);
        if (permissionBD == null)
            throw new NotFoundException("No se encontro el permiso con ID " + idPermission);

        return new ApiResponseDto(HttpStatus.OK.value(),"Información detallada del registro", this.permissionMapper.convertToDto(permissionBD));
    }

    @Override
    public ApiResponseDto executeGetListPermisisons() throws NotFoundException {
        List<PermissionEntity> permissions = this.permissionRepository.findAll();
        if (permissions.isEmpty())
            throw new NotFoundException("No hay registros en la tabla permisos");

        List<PermissionDto> permissionDtos = permissions.stream().map(permissionMapper::convertToDto).collect(Collectors.toList());
        return new ApiResponseDto(HttpStatus.OK.value(),"Lista de registros",permissionDtos);
    }

    @Override
    public ApiResponseDto executeListPermisisons(UUID idPermission, String name, String module, String status, int page, int size) throws NotFoundException {
        Pageable pageable = PageRequest.of(page,size);
        Specification<PermissionEntity> spec = SpecificationPermission.withFilter(idPermission,name,module,status);
        Page<PermissionEntity> listPermissions = this.permissionRepository.findAll(spec, pageable);

        if (listPermissions.isEmpty())
            throw new NotFoundException("No hay registros en la base de datos");

        // Convertimos los entities a DTOs
        List<PermissionDto> dtoList = listPermissions.getContent().stream().map(permissionMapper::convertToDto).collect(Collectors.toList());

        // Creamos el objeto de respuesta personalizada
        PermissionsListDto responseData = new PermissionsListDto();
        responseData.setPermissionDtos(dtoList);
        responseData.setTotalElements((int) listPermissions.getTotalElements());

        return new ApiResponseDto(HttpStatus.OK.value(), "Lista de permisos paginada", responseData);
    }

    @Override
    public ApiResponseDto executeCreatePermission(PermissionDto permissionDto, BindingResult bindingResult) throws BadRequestException {

        List<ValidateInputDto> listInputs = this.validateInputs(bindingResult);
        if (!listInputs.isEmpty())
            throw new BadRequestException("Campos invalidos",listInputs);


        PermissionEntity permissionBD = this.permissionRepository.findByName(permissionDto.getName()).orElse(null);
        if (permissionBD != null)
            throw new BadRequestException("Ya existe un permiso con el nombre " + permissionDto.getName());

        PermissionEntity save = this.permissionRepository.save(permissionMapper.convertToEntity(permissionDto));
        return new ApiResponseDto(HttpStatus.CREATED.value(),"El registro de inserto correctamente", permissionMapper.convertToDto(save));
    }

    @Override
    public ApiResponseDto executeUpdatePermisison(UUID idPermission, PermissionDto permissionDto, BindingResult bindingResult) throws BadRequestException, NotFoundException {

        List<ValidateInputDto> listInputs =  this.validateInputs(bindingResult);
        if (!listInputs.isEmpty())
            throw new BadRequestException("Campos invalidos", listInputs);

        PermissionEntity permissionBD = this.permissionRepository.findById(idPermission).orElse(null);
        if (permissionBD == null)
            throw new NotFoundException("No se encontro el permiso con ID "+ idPermission);

        permissionBD.setName(permissionDto.getName());
        permissionBD.setModule(permissionDto.getModule());
        permissionBD.setDescription(permissionDto.getDescription());
        permissionBD.setStatus(permissionDto.getStatus());
        PermissionEntity permissionEdit = this.permissionRepository.save(permissionBD);

        return new ApiResponseDto(HttpStatus.OK.value(),"El registro se actualizo de forma correcta",this.permissionMapper.convertToDto(permissionEdit));
    }

    @Override
    public ApiResponseDto executeDisabledPermission(UUID idPermission, DisabledPermissionDto disabledDto) throws NotFoundException {

        PermissionEntity permissionBD = this.permissionRepository.findById(idPermission).orElse(null);
        if (permissionBD == null)
            throw new NotFoundException("No se encontro el permiso con ID "+ idPermission);

        permissionBD.setStatus(disabledDto.getStatus());
        PermissionEntity permisisonDisabled = this.permissionRepository.save(permissionBD);

        return new ApiResponseDto(HttpStatus.OK.value(),"El registro cambio de estado de forma exitosa", this.permissionMapper.convertToDto(permisisonDisabled));

    }

    @Override
    public List<PermissionEntity> validatePermissions(List<PermissionDto> permissionDtos) throws BadRequestException, NotFoundException {
        List<PermissionEntity> validatePermissions = new ArrayList<>();

        for (PermissionDto dto : permissionDtos) {
            PermissionEntity permissionBD = this.permissionRepository.findById(dto.getId()).orElse(null);

            if (permissionBD == null)
                throw new NotFoundException("El permiso con nombre " + dto.getName() + " no existe");

            if (permissionBD.getStatus().equals(StatusPermisison.DELETED))
                throw new BadRequestException("No se puede asigar este el rol " + permissionBD.getName() + " porque esta con estatus eliminado");

            validatePermissions.add(permissionBD);
        }
        return validatePermissions;
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
