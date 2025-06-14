package com.ams.dev.api.parking.parkingLot.controller;

import com.ams.dev.api.parking.dto.ApiResponseDto;
import com.ams.dev.api.parking.exception.BadRequestException;
import com.ams.dev.api.parking.exception.NotFoundException;
import com.ams.dev.api.parking.parkingLot.dto.ParkingLotDto;
import com.ams.dev.api.parking.parkingLot.service.ParkingLotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/alls/select")
    public ResponseEntity<ApiResponseDto> executeGetListParkingsBySelect() throws NotFoundException {
        ApiResponseDto response = this.parkingLotService.executeGetListParkingBySelec();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/get/{parkingId}")
    public ResponseEntity<ApiResponseDto> executeGetParkingById(@PathVariable UUID parkingId) throws NotFoundException {
        ApiResponseDto response = this.parkingLotService.executeGetParkingById(parkingId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
