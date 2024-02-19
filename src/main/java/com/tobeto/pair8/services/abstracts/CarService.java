package com.tobeto.pair8.services.abstracts;

import com.tobeto.pair8.entities.concretes.Category;
import com.tobeto.pair8.services.dtos.car.requests.AddCarRequest;
import com.tobeto.pair8.services.dtos.car.requests.UpdateCarRequest;
import com.tobeto.pair8.services.dtos.car.responses.GetAllListCarResponse;
import com.tobeto.pair8.services.dtos.car.responses.GetByIdCarResponse;
import com.tobeto.pair8.services.dtos.car.responses.GetByPlateResponse;

import java.time.LocalDate;
import java.util.List;

public interface    CarService {

    void add(AddCarRequest addCarRequest);

    void delete(Integer id);

    void update(UpdateCarRequest updateCarRequest);

    List<GetAllListCarResponse> getAll();

    GetByIdCarResponse getById(int id);




    List<GetAllListCarResponse> getAvailableCars(LocalDate startDate, LocalDate endDate, Integer locationId);

    List<GetAllListCarResponse> getCategorizeCars(Category category);


    List<GetAllListCarResponse> getAvailableCarsByCategory(LocalDate startDate,
                                                           LocalDate endDate, Integer locationId, Category category,
                                                           Integer modelId, Integer brandId, Double minPrice, Double maxPrice);

    GetByPlateResponse getPlate(String plate);
}
