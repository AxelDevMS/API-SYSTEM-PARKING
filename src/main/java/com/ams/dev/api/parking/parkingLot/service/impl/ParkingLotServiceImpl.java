package com.ams.dev.api.parking.parkingLot.service.impl;

import com.ams.dev.api.parking.dto.ApiResponseDto;
import com.ams.dev.api.parking.dto.ValidateInputDto;
import com.ams.dev.api.parking.exception.BadRequestException;
import com.ams.dev.api.parking.exception.NotFoundException;
import com.ams.dev.api.parking.parkingLot.dto.DisabledParkingLotDto;
import com.ams.dev.api.parking.parkingLot.dto.ParkingLotDto;
import com.ams.dev.api.parking.parkingLot.dto.ParkingLotListDto;
import com.ams.dev.api.parking.parkingLot.mapper.ParkingLotMapper;
import com.ams.dev.api.parking.parkingLot.persistence.entity.ParkingLotEntity;
import com.ams.dev.api.parking.parkingLot.persistence.filter.ParkingLotFilter;
import com.ams.dev.api.parking.parkingLot.persistence.repository.ParkingLotRepository;
import com.ams.dev.api.parking.parkingLot.service.ParkingLotService;
import com.ams.dev.api.parking.parkingLot.util.StatusParkingLot;
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
public class ParkingLotServiceImpl implements ParkingLotService {

    @Autowired
    private ParkingLotMapper parkingLotMapper;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Override
    public ApiResponseDto executeUpdateParkingLot(UUID pparkingId, ParkingLotDto parkingLotDto, BindingResult bindingResult) throws NotFoundException, BadRequestException {
        ParkingLotEntity parkingDB = this.parkingLotRepository.findById(pparkingId).orElse(null);
        if (parkingDB == null)
            throw new NotFoundException("No se encontro este parking con ID "+ pparkingId);

        List<ValidateInputDto> inputDtos = this.validateInputs(bindingResult);
        if (!inputDtos.isEmpty())
            throw new BadRequestException("Campos invalidos", inputDtos);

        parkingDB.setName(parkingLotDto.getName());
        parkingDB.setLocation(parkingLotDto.getLocation());
        parkingDB.setCapacity(parkingLotDto.getCapacity());
        parkingDB.setStatus(parkingLotDto.getStatus());

        ParkingLotEntity parkingUpdate =  this.parkingLotRepository.save(parkingDB);

        return new ApiResponseDto(HttpStatus.OK.value(), "El parking se actualizo de forma exitosa", this.parkingLotMapper.convertToDto(parkingUpdate));
    }

    @Override
    public ApiResponseDto executeDisabledParkingLot(UUID parkingId, DisabledParkingLotDto disabledParkingLotDto) throws BadRequestException, NotFoundException {
        ParkingLotEntity parkingDB = this.parkingLotRepository.findById(parkingId).orElse(null);
        if (parkingDB == null)
            throw new NotFoundException("No existe este parking con ID "+ parkingId);

        if (StatusParkingLot.DELETED.equals(parkingDB.getStatus()))
            throw new BadRequestException("Este producto ya se encuentra eliminado");

        parkingDB.setStatus(disabledParkingLotDto.getStatus());
        ParkingLotEntity disabledParking = this.parkingLotRepository.save(parkingDB);

        return new ApiResponseDto(HttpStatus.OK.value(),"El parking se encuentra con estado eliminado",this.parkingLotMapper.convertToDto(disabledParking));
    }

    @Override
    public ApiResponseDto executeGetListParkingss(int page, int size, UUID parkingId, String status) throws NotFoundException {
        Pageable pageable = PageRequest.of(page,size);
        Specification<ParkingLotEntity> filterParking = ParkingLotFilter.wwithFilter(parkingId,status);
        Page<ParkingLotEntity> listParkingss = this.parkingLotRepository.findAll(filterParking,pageable);

        if (listParkingss.isEmpty())
            throw new NotFoundException("No tienes estacionamientos registrados");

        List<ParkingLotDto> parkingLotDtoList = listParkingss.getContent().stream().map(parkingLotMapper::convertToDto).collect(Collectors.toList());
        ParkingLotListDto data = new ParkingLotListDto();
        data.setParkings(parkingLotDtoList);
        data.setTotalElements((int) listParkingss.getTotalElements());
        return new ApiResponseDto(HttpStatus.OK.value(), "Listado de estacionamientos", data);
    }

    @Override
    public ApiResponseDto executeGetParkingById(UUID parkingId) throws NotFoundException {
        ParkingLotEntity parkingBD = this.parkingLotRepository.findById(parkingId).orElse(null);
        if (parkingBD == null)
            throw new NotFoundException("No existe este parking con ID "+ parkingId);

        return new ApiResponseDto(HttpStatus.OK.value(), "Información detallada del parking", this.parkingLotMapper.convertToDto(parkingBD));
    }

    @Override
    public ApiResponseDto executeGetListParkingBySelec() throws NotFoundException {
        List<ParkingLotEntity> listParkings  = this.parkingLotRepository.findAll();
        if (listParkings.isEmpty())
            throw new NotFoundException("No tienes estacionamientos registrados");

        List<ParkingLotDto> listParkingsDto = listParkings.stream().map(parkingLotMapper::convertToDto).collect(Collectors.toList());
        return new ApiResponseDto(HttpStatus.OK.value(), "Listado de estacionamientos", listParkingsDto);
    }

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
