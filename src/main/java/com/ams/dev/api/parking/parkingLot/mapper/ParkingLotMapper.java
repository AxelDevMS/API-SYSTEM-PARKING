package com.ams.dev.api.parking.parkingLot.mapper;

import com.ams.dev.api.parking.parkingLot.dto.ParkingLotDto;
import com.ams.dev.api.parking.parkingLot.persistence.entity.ParkingLotEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ParkingLotDto convertToDto(ParkingLotEntity parkingLotEntity){
        return this.modelMapper.map(parkingLotEntity,ParkingLotDto.class);
    }

    public ParkingLotEntity convertToEntity(ParkingLotDto parkingLotDto){
        return this.modelMapper.map(parkingLotDto,ParkingLotEntity.class);
    }


}
