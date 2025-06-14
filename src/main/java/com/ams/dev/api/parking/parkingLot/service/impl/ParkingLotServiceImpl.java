package com.ams.dev.api.parking.parkingLot.service.impl;

import com.ams.dev.api.parking.dto.ApiResponseDto;
import com.ams.dev.api.parking.dto.ValidateInputDto;
import com.ams.dev.api.parking.exception.BadRequestException;
import com.ams.dev.api.parking.parkingLot.dto.ParkingLotDto;
import com.ams.dev.api.parking.parkingLot.mapper.ParkingLotMapper;
import com.ams.dev.api.parking.parkingLot.persistence.entity.ParkingLotEntity;
import com.ams.dev.api.parking.parkingLot.persistence.repository.ParkingLotRepository;
import com.ams.dev.api.parking.parkingLot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    @Autowired
    private ParkingLotMapper parkingLotMapper;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Override
    public ApiResponseDto executeCreateParkingLot(ParkingLotDto parkingLotDto, BindingResult bindingResult) throws BadRequestException {
        List<ValidateInputDto> inputs = this.validateInputs(bindingResult);
        if (!inputs.isEmpty())
            throw new BadRequestException("Campos invallidos", inputs);

        ParkingLotEntity save = this.parkingLotRepository.save(this.parkingLotMapper.convertToEntity(parkingLotDto));
        return new ApiResponseDto(HttpStatus.CREATED.value(), "El parking se ha registrado de forma exitosa", this.parkingLotMapper.convertToDto(save));
    }

    private List<ValidateInputDto> validateInputs(BindingResult bindingResult){
        List<ValidateInputDto> inputs = new ArrayList<>();
        if (bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(input -> {
                ValidateInputDto inputDto = new ValidateInputDto();
                inputDto.setInput(input.getField());
                inputDto.setInvalidMessage(input.getDefaultMessage());
                inputs.add(inputDto);
            });
        }
        return inputs;
    }
}
