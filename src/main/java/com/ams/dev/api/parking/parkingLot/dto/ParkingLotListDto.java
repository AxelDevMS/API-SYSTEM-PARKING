package com.ams.dev.api.parking.parkingLot.dto;


import java.util.List;

public class ParkingLotListDto {

    private List<ParkingLotDto> parkings;
    private int totalElements;

    public List<ParkingLotDto> getParkings() {
        return parkings;
    }

    public void setParkings(List<ParkingLotDto> parkings) {
        this.parkings = parkings;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
}
