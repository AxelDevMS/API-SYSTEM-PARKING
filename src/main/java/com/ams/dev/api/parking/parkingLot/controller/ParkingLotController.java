package com.ams.dev.api.parking.parkingLot.controller;

import com.ams.dev.api.parking.dto.ApiResponseDto;
import com.ams.dev.api.parking.exception.BadRequestException;
import com.ams.dev.api.parking.parkingLot.dto.ParkingLotDto;
import com.ams.dev.api.parking.parkingLot.service.ParkingLotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/parking/lot")
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponseDto> executeCreateParkingLot(@RequestBody @Valid ParkingLotDto parkingLotDto, BindingResult bindingResult) throws BadRequestException {
        ApiResponseDto response = this.parkingLotService.executeCreateParkingLot(parkingLotDto, bindingResult);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
