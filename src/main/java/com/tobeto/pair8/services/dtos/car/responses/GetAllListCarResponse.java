package com.tobeto.pair8.services.dtos.car.responses;

import com.tobeto.pair8.entities.concretes.Category;
import com.tobeto.pair8.entities.concretes.FuelType;
import com.tobeto.pair8.entities.concretes.TransmissionType;
import com.tobeto.pair8.services.dtos.color.responses.GetColorNameResponse;
import com.tobeto.pair8.services.dtos.location.responses.GetAllLocation;
import com.tobeto.pair8.services.dtos.model.responses.GetAllListModelRespose;
import com.tobeto.pair8.services.dtos.model.responses.GetModelNameResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllListCarResponse {
    private int id;
    private Long kilometer;
    private String plate;
    private short modelYear;
    private Double dailyPrice;
    private short minFindeksRate;
    private String imagePath;
    private TransmissionType transmissionType;
    private FuelType fuelType;
    private Category category;
    private short passengerCapacity;
    private Double discount;
    private GetAllListModelRespose model;
    private GetColorNameResponse color;
    private GetAllLocation location;
}
