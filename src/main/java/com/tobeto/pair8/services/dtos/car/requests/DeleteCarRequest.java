package com.tobeto.pair8.services.dtos.car.requests;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarRequest {
    @Positive(message = "Lütfen geçerli bir id giriniz")
    private int id;
}
