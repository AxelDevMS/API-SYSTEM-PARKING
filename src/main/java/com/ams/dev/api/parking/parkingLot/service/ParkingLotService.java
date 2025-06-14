package com.ams.dev.api.parking.parkingLot.service;

import com.ams.dev.api.parking.dto.ApiResponseDto;
import com.ams.dev.api.parking.exception.BadRequestException;
import com.ams.dev.api.parking.exception.NotFoundException;
import com.ams.dev.api.parking.parkingLot.dto.ParkingLotDto;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface ParkingLotService {

    ApiResponseDto executeCreateParkingLot(ParkingLotDto parkingLotDto, BindingResult bindingResult) throws BadRequestException;
    ApiResponseDto executeGetListParkingBySelec() throws NotFoundException;
    ApiResponseDto executeGetParkingById(UUID parkingId) throws NotFoundException;

}
