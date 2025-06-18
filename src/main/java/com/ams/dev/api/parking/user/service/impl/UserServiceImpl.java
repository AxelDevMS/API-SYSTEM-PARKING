package com.ams.dev.api.parking.user.service.impl;

import com.ams.dev.api.parking.dto.ApiResponseDto;
import com.ams.dev.api.parking.dto.ValidateInputDto;
import com.ams.dev.api.parking.exception.BadRequestException;
import com.ams.dev.api.parking.exception.NotFoundException;
import com.ams.dev.api.parking.parkingLot.persistence.entity.ParkingLotEntity;
import com.ams.dev.api.parking.parkingLot.service.ParkingLotService;
import com.ams.dev.api.parking.parkingLot.util.StatusParkingLot;
import com.ams.dev.api.parking.role.persistence.entity.RoleEntity;
import com.ams.dev.api.parking.role.service.RoleService;
import com.ams.dev.api.parking.role.util.StatusRole;
import com.ams.dev.api.parking.user.dto.UserDto;
import com.ams.dev.api.parking.user.mapper.UserMapper;
import com.ams.dev.api.parking.user.persistence.entity.UserEntity;
import com.ams.dev.api.parking.user.persistence.repository.UserRepository;
import com.ams.dev.api.parking.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ParkingLotService parkingLotService;

    @Override
    public ApiResponseDto executeCreateUser(UserDto userDto, BindingResult bindingResult) throws BadRequestException, NotFoundException {
        List<ValidateInputDto> inputs = this.validateInputDtos(bindingResult);
        if (!inputs.isEmpty())
            throw new BadRequestException("Campos invalidos", inputs);

        this.validateUsernameAndEmail(userDto);

        if (!userDto.getPassword().equals(userDto.getConfirmPassword()))
            throw new BadRequestException("Las contraseñas no coninciden");

        RoleEntity roleBD = this.roleService.getRoleById(userDto.getRole().getId());
        if (roleBD == null || !roleBD.getStatus().equals(StatusRole.ACTIVE))
            throw new NotFoundException("No existe este rol o no esta activado, verifica por favor");

        ParkingLotEntity parkingLotBD = this.parkingLotService.getParkingById(userDto.getParking().getId());
        if (parkingLotBD == null || !parkingLotBD.getStatus().equals(StatusParkingLot.ACTIVE))
            throw new NotFoundException("No existe el estacionamiento o no esta activado, verifica por favor");

        UserEntity user = this.userMapper.convertToEntity(userDto);
        user.setRole(roleBD);
        user.setParking(parkingLotBD);
        user = this.userRepository.save(user);
        return new ApiResponseDto(HttpStatus.CREATED.value(),"El empleado se registro de forma existosa", this.userMapper.convertToDto(user));
    }

    private void validateUsernameAndEmail(UserDto userDto) throws BadRequestException {
        UserEntity userBD = this.userRepository.findByUsername(userDto.getUsername()).orElse(null);
        if (userBD != null)
            throw  new BadRequestException("Ya existe un usuario con este es username "+ userDto.getUsername());

        UserEntity userBDEmail = this.userRepository.findByEmail(userDto.getEmail()).orElse(null);
        if (userBDEmail != null)
            throw new BadRequestException("Ya existe un usuario con este correo electronico "+userDto.getEmail());
    }

    private void validateRoleAndParking(){

    }

    private List<ValidateInputDto> validateInputDtos(BindingResult bindingResult){
        List<ValidateInputDto> inputs = new ArrayList<>();
        if (bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(inputError ->{
                ValidateInputDto input = new ValidateInputDto();
                input.setInput(inputError.getField());
                input.setInvalidMessage(inputError.getDefaultMessage());
                inputs.add(input);
            });
        }
        return inputs;
    }
}
