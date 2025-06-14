package com.ams.dev.api.parking.parkingLot.service;

import com.ams.dev.api.parking.dto.ApiResponseDto;
import com.ams.dev.api.parking.exception.BadRequestException;
import com.ams.dev.api.parking.exception.NotFoundException;
import com.ams.dev.api.parking.parkingLot.dto.ParkingLotDto;
import org.springframework.validation.BindingResult;

public interface ParkingLotService {

    ApiResponseDto executeCreateParkingLot(ParkingLotDto parkingLotDto, BindingResult bindingResult) throws BadRequestException;
    ApiResponseDto executeGetListParkingBySelec() throws NotFoundException;

}
