package com.tobeto.pair8.controllers;

import com.tobeto.pair8.entities.concretes.Category;
import com.tobeto.pair8.entities.concretes.Location;
import com.tobeto.pair8.services.abstracts.CarService;
import com.tobeto.pair8.services.dtos.car.requests.AddCarRequest;
import com.tobeto.pair8.services.dtos.car.requests.DeleteCarRequest;
import com.tobeto.pair8.services.dtos.car.requests.UpdateCarRequest;
import com.tobeto.pair8.services.dtos.car.responses.GetAllListCarResponse;
import com.tobeto.pair8.services.dtos.car.responses.GetByIdCarResponse;
import com.tobeto.pair8.services.dtos.car.responses.GetByPlateResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/cars")
@AllArgsConstructor
@CrossOrigin
public class CarsController {
    private CarService carService;

    @GetMapping
    public List<GetAllListCarResponse> getAll() {
        return carService.getAll();
    }

    @GetMapping("/{plate}")
    public GetByPlateResponse getCarByPlate(@PathVariable String plate) {
        return carService.getPlate(plate);
    }

    @GetMapping("/getById")
    public GetByIdCarResponse getById(@RequestParam @Valid int id) {
        return carService.getById(id);
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddCarRequest addCarRequest) {
        carService.add(addCarRequest);
    }

    @DeleteMapping
    public void delete(@RequestBody @Valid DeleteCarRequest deleteCarRequest) {
        carService.delete(deleteCarRequest);
    }

    @PutMapping
    public void update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
        carService.update(updateCarRequest);
    }


    @GetMapping("/available")
    public List<GetAllListCarResponse> getAvailableCars(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam Integer locationId) {
        return carService.getAvailableCars(startDate, endDate, locationId);
    }


    @GetMapping("/category")
    public List<GetAllListCarResponse> getCategorizeCars(@RequestParam Category category) {
        return carService.getCategorizeCars(category);
    }

    @GetMapping("/availableByCategory")
    public List<GetAllListCarResponse> getAvailableCarsByCategory(
            @RequestParam  LocalDate startDate,
            @RequestParam  LocalDate endDate,
            @RequestParam(required = false) Integer locationId,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Integer brandId,
            @RequestParam(required = false) Integer modelId,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice)
    {
        return carService.getAvailableCarsByCategory(startDate, endDate, locationId, category, modelId, brandId, minPrice, maxPrice);
    }

}
