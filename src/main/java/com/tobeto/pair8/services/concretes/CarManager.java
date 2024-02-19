package com.tobeto.pair8.services.concretes;

import com.tobeto.pair8.core.utilities.mappers.services.ModelMapperService;
import com.tobeto.pair8.entities.concretes.Car;
import com.tobeto.pair8.entities.concretes.Category;
import com.tobeto.pair8.repositories.CarRepository;
import com.tobeto.pair8.rules.car.CarBusinessRulesMenager;
import com.tobeto.pair8.services.abstracts.CarService;
import com.tobeto.pair8.services.dtos.car.requests.AddCarRequest;
import com.tobeto.pair8.services.dtos.car.requests.UpdateCarRequest;
import com.tobeto.pair8.services.dtos.car.responses.GetAllListCarResponse;
import com.tobeto.pair8.services.dtos.car.responses.GetByIdCarResponse;
import com.tobeto.pair8.services.dtos.car.responses.GetByPlateResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository carRepository;
    private final ModelMapperService modelMapperService;
    private final CarBusinessRulesMenager carBusinessRulesMenager;

    @Override
    public List<GetAllListCarResponse> getAll() {
        List<Car> cars = carRepository.findAll();
        List<GetAllListCarResponse> carResponses = cars.stream()
                .map(car -> this.modelMapperService
                        .forResponse().map(car, GetAllListCarResponse.class))
                .collect(Collectors.toList());
        return carResponses;
    }


    @Override
    public GetByIdCarResponse getById(int id) {
        Car car = carRepository.findById(id).orElseThrow();
        GetByIdCarResponse carResponses = this.modelMapperService.forResponse().map(car, GetByIdCarResponse.class);
        return carResponses;
    }



    @Override
    public void add(AddCarRequest addCarRequest) {
        carBusinessRulesMenager.exceptionSamePlate(addCarRequest.getPlate());
        Car car = this.modelMapperService.forRequest().map(addCarRequest, Car.class);

        carRepository.save(car);
    }

    @Override
    public void delete(Integer id) {
        Car carToDelete = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Araç bulunamadı, ID: " + id));

        carRepository.delete(carToDelete);
    }

    @Override
    public void update(UpdateCarRequest updateCarRequest) {
        Car carToUpdate = carRepository.findById(updateCarRequest.getId())
                .orElseThrow();

        this.modelMapperService.forRequest().map(updateCarRequest, carToUpdate);
        carRepository.saveAndFlush(carToUpdate);

    }


    @Override
    public List<GetAllListCarResponse> getAvailableCars(LocalDate startDate, LocalDate endDate, Integer locationId) {
        return carRepository.findAvailableCars(startDate, endDate, locationId);
    }
    @Override
    public List<GetAllListCarResponse> getCategorizeCars(Category category) {
        return carRepository.findByCategory(category);
    }
    @Override
    public List<GetAllListCarResponse> getAvailableCarsByCategory(LocalDate startDate, LocalDate endDate, Integer locationId, Category category, Integer modelId, Integer brandId, Double minPrice, Double maxPrice) {
        return carRepository.findAvailableCarsByCategory(startDate, endDate, locationId, category, modelId, brandId, minPrice, maxPrice);
    }

    @Override
    public GetByPlateResponse getPlate(String plate) {
        return carRepository.findPlate(plate);
    }

}
